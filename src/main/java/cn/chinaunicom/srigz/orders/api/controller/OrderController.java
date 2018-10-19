package cn.chinaunicom.srigz.orders.api.controller;

import cn.chinaunicom.srigz.orders.Entity.OrderQueryParam;
import cn.chinaunicom.srigz.orders.Service.OrderService;
import cn.chinaunicom.srigz.orders.api.controller.query.OrderPageQuery;
import cn.chinaunicom.srigz.orders.common.utils.ActionHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "订单管理" ,tags = {"订单的接口"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * 根据多个参数查询订单分页列表
     * @param
     * @return
     */
    @ApiOperation(value="查询订单列表", notes="根据参数取得的订单列表")
    @RequestMapping(value = "/api/v1/orders/query" ,method = RequestMethod.GET)
    public Map getOrders(@ApiParam(required=true,value="订单入参信息") @ModelAttribute OrderQueryParam orderQueryParam, OrderPageQuery orderPageQuery){
        return ActionHelper.responseOk(orderService.getOrders(orderPageQuery.getPage(),orderPageQuery.getPageSize(),orderQueryParam));
    }

    /***
     * 根据orderId查询订单列表
     * @param
     * @return
     */
    @ApiOperation(value="查询订单详细", notes="根据OrderId取得的订单详情")
    @RequestMapping(value = "/api/v1/orders/{orderId}" ,method = RequestMethod.GET)
    public Map getOrderByorderId(@ApiParam(required = true, value = "订单ID") @PathVariable String orderId){
        return ActionHelper.responseOk(orderService.getOrderByOrderId(orderId));
    }

    /***
     * 按选中订单的领单
     * @param userId
     * @param orderIds
     * @return
     */
    @ApiOperation(value="按选中订单领单", notes="根据选中的订单领单")
    @RequestMapping(value = "/api/v1/orders/claimChoose" ,method = RequestMethod.POST)
    public Map claimChoose(@ApiParam(required = true, value = "用户ID") @RequestParam Long userId,
                               @ApiParam(required = true, value = "订单IDS") @RequestParam(value = "orderIds") List<String> orderIds){
        //TODO 遍历领单，并返回失败领单的orderIds
        List list = orderService.claimChoose(userId, orderIds);
        if(list.size() > 0){
            return ActionHelper.responseFailed("订单领单操作失败");
        }
        return ActionHelper.responseOk();
    }

    /***
     * 根据输入的数量按条件领单
     * @param userId
     * @param claimNumber
     * @param orderIds
     * @return
     */
    @ApiOperation(value="按数量订单领单", notes="根据输入的数量按条件领单")
    @RequestMapping(value = "/api/v1/orders/claimByNumber" ,method = RequestMethod.POST)
    public Map claimByNumber(@ApiParam(required = true, value = "用户ID") @RequestParam Long userId
            , @ApiParam(required = true, value = "领取数量") @RequestParam(value = "claimNumber",required=true) Integer claimNumber,
                             @ApiParam(required = true, value = "订单IDS") @RequestParam(value = "orderIds")  List<String> orderIds,
                             @ApiParam(required=true,value="订单入参信息") @ModelAttribute OrderQueryParam orderQueryParam){
        //TODO 1 先领取选中的订单，返回领取成功的数量  2 根据查询条件查询出（填写数量—选中领取成功的数量）的列表，再遍历领取
        orderService.claimByNumber(userId, claimNumber, orderIds, orderQueryParam);
        return ActionHelper.responseOk();
    }

    /***
     * 订单解锁
     * @param orderIds
     * @return
     */
    @ApiOperation(value="订单解锁", notes="根据订单id解锁")
    @RequestMapping(value = "/api/v1/orders/unLock" ,method = RequestMethod.POST)
    public Map unLock(@ApiParam(required = true, value = "订单IDS") @RequestParam(value = "orderIds")  List<String> orderIds){

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

}
