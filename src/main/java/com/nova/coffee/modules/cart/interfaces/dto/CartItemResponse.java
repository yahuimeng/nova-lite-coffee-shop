package com.nova.coffee.modules.cart.interfaces.dto;

import java.math.BigDecimal;

/**
 * 购物车项响应。
 */
public record CartItemResponse(
    String productCode,
    String productName,
    int quantity,
    BigDecimal unitPrice
) {
}
