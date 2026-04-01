package com.nova.coffee.modules.cart.interfaces.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车响应。
 */
public record CartResponse(
    Long memberId,
    List<CartItemResponse> items,
    BigDecimal totalAmount
) {
}
