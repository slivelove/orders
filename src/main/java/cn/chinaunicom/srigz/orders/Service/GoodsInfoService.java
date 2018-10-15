package cn.chinaunicom.srigz.orders.Service;

import cn.chinaunicom.srigz.orders.Entity.GoodsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsInfoService extends JpaRepository<GoodsInfo,String> {

}
