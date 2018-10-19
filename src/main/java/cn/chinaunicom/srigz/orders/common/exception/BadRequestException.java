package cn.chinaunicom.srigz.orders.common.exception;

/**
 * Created by jasper on 2018-04-21.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message, Throwable cause) {
        super(message);
        this.initCause(cause);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
