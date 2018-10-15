package cn.chinaunicom.srigz.orders.api.controller;

import cn.chinaunicom.srigz.orders.Entity.OrderInfo;
import cn.chinaunicom.srigz.orders.Service.OrderInfoService;
import cn.chinaunicom.srigz.orders.api.controller.query.OrderPageQuery;
import cn.chinaunicom.srigz.orders.common.utils.ActionHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@RestController
@Api(value = "订单管理" ,tags = {"订单的接口"})
public class OrderController {
    /***
     * 订单列表
     * @param query
     * @return
     */

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value="查询订单列表", notes="根据参数取得的订单列表")
    @RequestMapping(value = "/api/v1/orders/query" ,method = RequestMethod.GET)
    public Page<JSONObject> selectOrders(){
        List<JSONObject> list=new ArrayList<>();

        JSONObject object=new JSONObject();
//        object.put("name","订单1");
//        list.add(object);
        //TODO
        List<OrderInfo> cs=orderInfoService.findAll();
        //List<Object> aa = beanToObject(cs);
        for(OrderInfo orderInfo: cs){
            JSONObject a= (JSONObject) JSON.toJSON(orderInfo);
            list.add(a);
        }
        Page<JSONObject> p = new PageImpl<JSONObject>(list);
        return  p;
    }


    @ApiOperation(value="查询订单详细", notes="根据OrderId取得的订单详情")
    @RequestMapping(value = "/api/v1/orders/{orderId}" ,method = RequestMethod.GET)
    public JSONObject getOrderByorderId(@ApiParam(required = true, value = "订单ID") @PathVariable String orderId){
        JSONObject object=new JSONObject();
        OrderInfo cs=orderInfoService.findByorderId(orderId);
        JSONObject a= (JSONObject) JSON.toJSON(cs);
        return a;
    }

    /***
     * 领单
     * @param orderId
     * @param luckUserId
     */
    @ApiOperation(value="订单领单", notes="用户根据OrderId领取订单")
    @RequestMapping(value = "/api/v1/orders/claim" ,method = RequestMethod.POST)
    public JSONObject claim(@ApiParam(required = true, value = "订单ID") @RequestParam String orderId,
                       @ApiParam(required = true, value = "用户ID") @RequestParam String luckUserId ){
        JSONObject object=new JSONObject();
        orderInfoService.lockOrderByorderId(luckUserId,orderId);
        OrderInfo cs = orderInfoService.findByorderId(orderId);
        JSONObject a= (JSONObject) JSON.toJSON(cs);
        return a;
    }

    /***
     * 按选中订单的领单
     * @param userId
     * @param ids
     * @return
     */
    @ApiOperation(value="按选中订单领单", notes="根据选中的订单领单")
    @RequestMapping(value = "/api/v1/orders/claimChoose" ,method = RequestMethod.POST)
    public  Object claimChoose(@ApiParam(required = true, value = "用户ID") @RequestParam Long userId,
                                    @ApiParam(required = true, value = "订单IDS") @RequestParam(value = "ids") List[] ids
                                    ){
        //TODO 遍历领单，并返回成功领单的ids

        return ActionHelper.responseOk();
    }

    /***
     * 根据输入的数量按条件领单
     * @param userId
     * @param ids
     * @return
     */
    @ApiOperation(value="按数量订单领单", notes="根据输入的数量按条件领单")
    @RequestMapping(value = "/api/v1/orders/claimByNumber" ,method = RequestMethod.POST)
    public  Object claimByNumber(OrderPageQuery query, @ApiParam(required = true, value = "用户ID") @RequestParam Long userId
                                    , @ApiParam(required = true, value = "领取数量") @RequestParam(value = "number",required=true) Integer number,
                                 @ApiParam(required = true, value = "订单IDS") @RequestParam(value = "ids")  List[] ids){
        //TODO 1 先领取选中的订单，返回领取成功的数量  2 根据查询条件查询出（填写数量—选中领取成功的数量）的列表，再遍历领取
        return ActionHelper.responseOk();
    }

    /***
     * 订单解锁
     * @param ids
     * @return
     */
    @ApiOperation(value="订单解锁", notes="根据订单id解锁")
    @RequestMapping(value = "/api/v1/orders/unLock" ,method = RequestMethod.POST)
    public  Object unLock(@ApiParam(required = true, value = "订单IDS") @RequestParam(value = "ids")  List[] ids){
        return ActionHelper.responseOk();

    }

    /***
     * 订单分配
     * @param userId
     * @param ids
     * @return
     */
    @ApiOperation(value="订单分配", notes="根据选中的订单进行订单分配")
    @RequestMapping(value = "/api/v1/orders/distribute" ,method = RequestMethod.POST)
    public  Object distribute(@ApiParam(required = true, value = "用户ID") @RequestParam Long userId,
                                   @ApiParam(required = true, value = "订单IDS") @RequestParam(value = "ids") List[] ids){
        return ActionHelper.responseOk();
    }

    @ApiOperation(value="订单处理", notes="订单处理")
    @RequestMapping(value = "/api/v1/orders/deal" ,method = RequestMethod.POST)
    public Object deal(@ApiParam(required = true, value = "订单ID") @RequestParam Long userId){
        return ActionHelper.responseOk();
    }
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<OrderInfo> test(){
        OrderInfo cs = orderInfoService.findByorderId("orderId") ;
        List<OrderInfo> list = new ArrayList<>();
        list.add(cs);
        return list;
    }

}
