package cn.chinaunicom.srigz.orders.dao;


import cn.chinaunicom.srigz.orders.Entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRepository extends JpaRepository<OrderInfo,String>, JpaSpecificationExecutor<OrderInfo> {

}
