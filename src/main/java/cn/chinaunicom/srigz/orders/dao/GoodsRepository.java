package cn.chinaunicom.srigz.orders.dao;


import cn.chinaunicom.srigz.orders.Entity.GoodsInfo;
import cn.chinaunicom.srigz.orders.Entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GoodsRepository extends JpaRepository<GoodsInfo,String>, JpaSpecificationExecutor<OrderInfo> {

}
