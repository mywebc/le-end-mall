package com.chenxiaolani.mall.exception;

/**
 * 统一的异常
 */
public class LeMallException extends Exception {
    private final Integer code;
    private final String msg;

    public LeMallException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LeMallException(LeMallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
