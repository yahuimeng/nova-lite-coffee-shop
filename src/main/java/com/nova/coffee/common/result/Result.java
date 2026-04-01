package com.nova.coffee.common.result;

import com.nova.coffee.common.enums.ResultCode;

/**
 * 统一返回对象。
 *
 * @param code 响应码
 * @param message 响应信息
 * @param data 业务数据
 * @param success 是否成功
 * @param <T> 数据类型
 */
public record Result<T>(String code, String message, T data, boolean success) {

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, true);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data, true);
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null, false);
    }

    public static <T> Result<T> failure(String code, String message) {
        return new Result<>(code, message, null, false);
    }
}
