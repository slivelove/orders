package cn.chinaunicom.srigz.orders.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Entity
public class GoodsInfo {
  @Id
  @GeneratedValue
  private String goodsSku;
  private String goodsName;
  private String certiNum;
  private String newCust;
  private String custName;
  private String custType;
  private String shippingType;

  public GoodsInfo(){

  }

}
