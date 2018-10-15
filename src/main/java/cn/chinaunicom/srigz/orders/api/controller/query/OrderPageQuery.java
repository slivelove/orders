package cn.chinaunicom.srigz.orders.api.controller.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "订单分页列表查询对象")
public class OrderPageQuery extends OrderListQuery implements PageQuery {
    @ApiModelProperty(name = "page",value="页码",dataType = "int",required = true)
    private Integer page=0;
    @ApiModelProperty(name = "pageSize" ,value = "分页大小",dataType = "int",required = true)
    private  Integer pageSize=20;

    @Override
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
