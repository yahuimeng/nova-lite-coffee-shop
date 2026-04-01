package com.nova.coffee.modules.promotion.domain.model;

import java.math.BigDecimal;

/**
 * 优惠券信息。
 */
public record CouponInfo(
    String couponCode,
    String title,
    BigDecimal discountAmount,
    String status
) {
}
