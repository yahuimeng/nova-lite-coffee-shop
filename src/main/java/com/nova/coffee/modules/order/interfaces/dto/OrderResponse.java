package com.nova.coffee.modules.order.interfaces.dto;

import java.math.BigDecimal;

/**
 * 订单响应对象。
 */
public record OrderResponse(
    String orderNo,
    String productCode,
    String productName,
    int quantity,
    BigDecimal totalAmount,
    String status,
    String tenantId
) {
}
