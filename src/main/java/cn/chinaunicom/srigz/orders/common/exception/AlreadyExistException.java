package cn.chinaunicom.srigz.orders.common.exception;

/**
 * Created by jasper on 2018-04-20.
 */
public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String objectName) {
        super(objectName + " already exists !");
    }
}
