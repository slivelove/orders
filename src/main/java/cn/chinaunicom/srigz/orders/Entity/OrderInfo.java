package cn.chinaunicom.srigz.orders.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1063821955023696541L;

    @Id
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
    private String flowNodeCode;
    private Integer status;
    private Long createUserId;
    @Column(name = "create_dt", columnDefinition = "DATETIME")
    private Date createDt;
    @Column(name = "update_dt", columnDefinition = "DATETIME")
    private Date updateDt;
    private String visibleStatus;
    private Long lockUserId;
    private String lockUserName;
    @Column(name = "claim_dt", columnDefinition = "DATETIME")
    private Date claimDt;
    private Integer verifyFlag;
    private String createUserNo;
    private String createUserName;
    private Long operUserId;
    private String operUserName;
    private Long amount;
    private Long disacount;
    private Long payMoney;
    private String payType;
    private String paymentType;
    private Integer payStatus;
    private Integer exceptionType;
    private String lockUserNo;
    private String flowCode;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private List<GoodsInfo> goodsInfos;
}
