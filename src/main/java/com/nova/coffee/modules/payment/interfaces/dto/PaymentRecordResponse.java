package com.nova.coffee.modules.payment.interfaces.dto;

import java.math.BigDecimal;

/**
 * 支付记录响应。
 */
public record PaymentRecordResponse(
    String paymentNo,
    String orderNo,
    BigDecimal amount,
    String channel,
    String status
) {
}
