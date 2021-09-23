package com.chenxiaolani.mall.exception;


/**
 * 异常枚举
 */
public enum LeMallExceptionEnum {
    NEED_USER_NAME(1001, "用户名不能为空");
    /**
     * 异常码
     */
    Integer code;

    /**
     * 异常信息
     */
    String msg;

    LeMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LeMallExceptionEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
