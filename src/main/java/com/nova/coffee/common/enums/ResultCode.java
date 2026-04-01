package com.nova.coffee.common.enums;

/**
 * 统一响应码定义。
 */
public enum ResultCode {

    SUCCESS("0000", "操作成功"),
    BAD_REQUEST("4000", "请求参数错误"),
    UNAUTHORIZED("4010", "未认证或认证已失效"),
    FORBIDDEN("4030", "无权访问"),
    BIZ_ERROR("5001", "业务处理失败"),
    SYSTEM_ERROR("5000", "系统繁忙，请稍后再试");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
