package com.chenxiaolani.mall.exception;

import com.chenxiaolani.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


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

    // 接口参数异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ApiRestResponse handleBindingResult(BindingResult result) {
        // 把异常处理为对外暴露的统一的提示
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String defaultMessage = objectError.getDefaultMessage();
                list.add(defaultMessage);
            }
        }
        if (list.size() == 0) {
            return ApiRestResponse.error(LeMallExceptionEnum.REQUEST_PARAMS_ERROR);
        }
        return ApiRestResponse.error(LeMallExceptionEnum.REQUEST_PARAMS_ERROR.getCode(), list.toString());
    }
}
