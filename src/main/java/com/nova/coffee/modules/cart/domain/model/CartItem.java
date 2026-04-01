package com.nova.coffee.modules.cart.domain.model;

import java.math.BigDecimal;

/**
 * 购物车项。
 */
public record CartItem(
    String productCode,
    String productName,
    int quantity,
    BigDecimal unitPrice
) {
}
