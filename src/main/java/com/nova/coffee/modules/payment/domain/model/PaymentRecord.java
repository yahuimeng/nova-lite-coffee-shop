package com.nova.coffee.modules.payment.domain.model;

import java.math.BigDecimal;

/**
 * 支付记录。
 */
public record PaymentRecord(
    String paymentNo,
    String orderNo,
    BigDecimal amount,
    String channel,
    String status
) {
}
