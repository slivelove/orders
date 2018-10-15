package cn.chinaunicom.srigz.orders.Service;

import cn.chinaunicom.srigz.orders.Entity.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderInfoService extends JpaRepository<OrderInfo,String>,JpaSpecificationExecutor<OrderInfo>{
    @Query(value="SELECT * from order_info where order_id=?1",nativeQuery=true)
    OrderInfo findByorderId(String orderId);

    @Modifying
    @Transactional
    @Query(value="UPDATE order_info SET lock_user_id = ?1 WHERE order_id = ?2",nativeQuery = true)
    void lockOrderByorderId(String luckUseId,String orderId);

    @Modifying
    @Query(value="UPDATE order_info SET lock_user_id = NULL WHERE order_id = ?1",nativeQuery = true)
    void unlockOrderByOrderId(String orderId);


}
