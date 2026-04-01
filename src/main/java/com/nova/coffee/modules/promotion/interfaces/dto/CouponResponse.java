package com.nova.coffee.modules.promotion.interfaces.dto;

import java.math.BigDecimal;

/**
 * 优惠券响应。
 */
public record CouponResponse(
    String couponCode,
    String title,
    BigDecimal discountAmount,
    String status
) {
}
