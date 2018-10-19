package cn.chinaunicom.srigz.orders.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class GoodsInfo implements Serializable {
  private static final long serialVersionUID = -4320456485143228242L;

  @Id
  private String goodsSku;
  private String orderId;
  private String goodsName;
  private String certiNum;
  private String newCust;
  private String custName;
  private String custType;
  private String shippingType;

}
