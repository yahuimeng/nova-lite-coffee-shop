package com.nova.coffee.modules.cart.domain.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车聚合。
 */
public record ShoppingCart(
    Long memberId,
    List<CartItem> items
) {

    public BigDecimal totalAmount() {
        return items.stream()
            .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
