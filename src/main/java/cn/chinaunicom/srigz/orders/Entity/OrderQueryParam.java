package cn.chinaunicom.srigz.orders.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryParam implements Serializable {
    private static final long serialVersionUID = 5061408482334296786L;

    private Date createStartDate;
    private Date createEndDate;
    private String goodsName;
    private String custType;
    private String custName;
    private String orderCityCode;
    private String visibleStatus;
    private Integer verifyFlag;
    private Integer status;
}
