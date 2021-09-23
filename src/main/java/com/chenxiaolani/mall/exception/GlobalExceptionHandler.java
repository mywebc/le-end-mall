package com.chenxiaolani.mall.exception;

import com.chenxiaolani.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局处理统一异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 系统异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception", e);
        return ApiRestResponse.error(LeMallExceptionEnum.SYSTEM_ERROR);
    }

    // 业务异常
    @ExceptionHandler(LeMallException.class)
    @ResponseBody
    public Object handleLeMallException(LeMallException e) {
        log.error("LeMall Exception", e);
        return ApiRestResponse.error(e.getCode(), e.getMsg());
    }
}
