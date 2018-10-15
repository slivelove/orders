package cn.chinaunicom.srigz.orders.api.controller.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

public interface PageQuery {
    Integer getPage();

    Integer getPageSize();
}
