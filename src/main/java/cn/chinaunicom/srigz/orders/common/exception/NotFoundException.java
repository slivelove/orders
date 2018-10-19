package cn.chinaunicom.srigz.orders.common.exception;

/**
 * Created by jasper on 2018-04-20.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String objectName) {
        super(objectName + " not found !");
    }

    public NotFoundException(String objectName, Object id) {
        super(objectName + "[id=" + id.toString() + "] not found !");
    }
}
