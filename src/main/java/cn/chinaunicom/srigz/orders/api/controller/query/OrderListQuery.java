package cn.chinaunicom.srigz.orders.api.controller.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单列表查询对象")
public class OrderListQuery {
    @ApiModelProperty(name ="flowId",value = "流程环节")
    private String flowId;
    @ApiModelProperty(name ="orderId",value = "内部订单号")
    private String orderId;
}
