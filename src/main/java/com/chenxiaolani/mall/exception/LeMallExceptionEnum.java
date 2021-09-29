package com.chenxiaolani.mall.exception;


/**
 * 异常枚举
 */
public enum LeMallExceptionEnum {
    NEED_USER_NAME(1001, "用户名不能为空"),
    NEED_PASSWORD(1002, "密码不能为空"),
    NEED_PASSWORD_TOO_SHORT(1003, "密码不能小于8位"),
    NAME_EXISTED(1004, "不允许重名"),
    INSERT_FAILED(1005, "插入失败，请重试"),
    WRONG_PASSWORD(1006, "密码错误"),
    NEED_LOGIN(1007, "用户未登录"),
    UPDATE_FAILED(1008, "更新失败"),
    NEED_ADMIN(1009, "无管理员权限"),
    PARAMS_NOT_NULL(1010, "参数不能为空"),
    CATEGORY_FAILED(1011, "新增失败"),
    REQUEST_PARAMS_ERROR(1012, "参数错误"),
    DELETE_FAILED(1013, "删除失败"),
    SYSTEM_ERROR(2000, "系统异常");

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
