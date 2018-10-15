package cn.chinaunicom.srigz.orders.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class OrderInfo {
    @Id
    @GeneratedValue
    private String orderId;
    private String sourceId;
    private String serialNo;
    private String sourceSystem;
    private String channelMark;
    private String custType;
    private String custName;
    private String goodsSku;
    private String orderProvincCode;
    private String orderCityCode;
    private String flowId;
    private Integer status;
    private BigInteger createUserId;
    private Date createDt;
    private BigInteger opeUserId;
    private Date updateDt;
    private String visibleStatus;
    private BigInteger lockUserId;
    private String lockUserName;
    private Date claimDt;

    public OrderInfo() {
    }


}
