package com.nova.coffee.common.exception;

import com.nova.coffee.common.enums.ResultCode;

/**
 * 业务异常。
 */
public class BizException extends RuntimeException {

    private final String code;

    public BizException(String message) {
        this(ResultCode.BIZ_ERROR.getCode(), message);
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
