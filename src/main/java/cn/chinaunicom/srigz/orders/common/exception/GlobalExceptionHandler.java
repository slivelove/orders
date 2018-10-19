package cn.chinaunicom.srigz.orders.common.exception;

import cn.chinaunicom.srigz.orders.common.utils.ActionHelper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jasper on 2018-04-20.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler
    public JSONObject handleException(Exception e, HttpServletResponse response) {
        return ActionHelper.responseFailed(e.getMessage());
    }
}
