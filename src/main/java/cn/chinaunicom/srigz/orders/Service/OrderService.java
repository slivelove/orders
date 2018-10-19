package cn.chinaunicom.srigz.orders.Service;

import cn.chinaunicom.srigz.orders.Entity.GoodsInfo;
import cn.chinaunicom.srigz.orders.Entity.OrderInfo;
import cn.chinaunicom.srigz.orders.Entity.OrderQueryParam;
import cn.chinaunicom.srigz.orders.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
@EnableAutoConfiguration
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /**
     * 多条件分页查询
     * @param pageNum
     * @param pageSize
     * @param orderQueryParam
     * @return
     */
    public Page<OrderInfo> getOrders(Integer pageNum,Integer pageSize,OrderQueryParam orderQueryParam){
        Specification<OrderInfo> specification = new Specification<OrderInfo>() {
            @Override
            public Predicate toPredicate(Root<OrderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 建立子查询
                Subquery<GoodsInfo> goodsSubquery = query.subquery(GoodsInfo.class);
                Root<GoodsInfo> goodsInfoRoot = goodsSubquery.from(GoodsInfo.class);
                goodsSubquery.select(goodsInfoRoot.get("goodsSku"));

                List<Predicate> orderInfo = new ArrayList<Predicate>();
                List<Predicate> goodsInfo = new ArrayList<Predicate>();
                Date createStartDate = orderQueryParam.getCreateStartDate();
                Date createEndDate = orderQueryParam.getCreateEndDate();
                String custName = orderQueryParam.getCustName();
                String goodsName = orderQueryParam.getGoodsName();
                String custType = orderQueryParam.getCustType();
                Integer status = orderQueryParam.getStatus();
                if (createStartDate != null && createEndDate != null) {
                    orderInfo.add(cb.between(root.get("createDt"),createStartDate,createEndDate));
                }
                if(custName != null){
                    orderInfo.add(cb.like(root.get("custName"),"%"+custName+"%"));
                }
                if(goodsName != null){
                    goodsInfo.add(cb.like(goodsInfoRoot.get("goodsName"),"%"+goodsName+"%"));
                }
                if(custType != null){
                    orderInfo.add(cb.equal(root.get("custType").as(String.class),custType));
                }
                if(status != null){
                    orderInfo.add(cb.equal(root.get("status").as(Integer.class),status));
                }
                if(goodsInfo.size() > 0){
                    // 子查询与父查询相关联
                    goodsInfo.add(cb.equal(goodsInfoRoot.get("orderId"),root.get("orderId")));
                    // 拼接过滤条件
                    goodsSubquery.where(goodsInfo.toArray(new Predicate[goodsInfo.size()]));
                    // 和总条件拼接（exists的使用）
                    orderInfo.add(cb.exists(goodsSubquery));
                }
                return query.where(orderInfo.toArray(new Predicate[orderInfo.size()])).getRestriction();
            }
        };
        Page<OrderInfo> orders = orderRepository.findAll(specification, PageRequest.of(pageNum - 1, pageSize));
        return orders;
    }

    /**
     * 根据orderId查询订单信息
     * @param orderId
     * @return
     */
    public OrderInfo getOrderByOrderId(String orderId){
        Optional<OrderInfo> order = orderRepository.findById(orderId);
        return order.get();
    }

    /***
     * 修改单个订单
     * @param userId
     * @param orderId
     * @return
     */
    @Transactional
    public Boolean updateOne(Long userId,String orderId){
        OrderInfo orderInfo = orderRepository.findById(orderId).get();
        if(orderInfo.getStatus() == 0 && orderInfo.getLockUserId() == null) {
            orderInfo.setStatus(1);
            orderInfo.setLockUserId(userId);
            orderRepository.save(orderInfo);
        }else{
            return false;
        }
        return true;
    }




    /***
     * 按选中订单的领单
     * @param userId
     * @param orderIds
     * @return
     */
    public List claimChoose(Long userId,List<String> orderIds){
        List<String> list = new ArrayList<>();
        for (String orderId: orderIds) {
            Boolean b = updateOne(userId, orderId);
            if(!b){
                list.add(orderId);
            }
        }
        return list;
    }

    /***
     * 根据输入的数量按条件领单、分配
     * @param userId
     * @param claimNumber
     * @param orderIds
     * @return
     */
    public void claimByNumber(Long userId,Integer claimNumber,List<String> orderIds,OrderQueryParam orderQueryParam) {
        List list = claimChoose(userId, orderIds);
        Integer success = orderIds.size() - list.size();
        if (claimNumber > success) {
            List<OrderInfo> orders = getOrders(1, claimNumber - success, orderQueryParam).getContent();
            List<String> ids = new ArrayList<>();
            for (OrderInfo orderInfo : orders) {
                ids.add(orderInfo.getOrderId());
            }
            claimChoose(userId,ids);
        }
    }

}
