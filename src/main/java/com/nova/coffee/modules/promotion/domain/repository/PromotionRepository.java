package com.nova.coffee.modules.promotion.domain.repository;

import com.nova.coffee.modules.promotion.domain.model.CouponInfo;
import java.util.List;

/**
 * 营销仓储接口。
 */
public interface PromotionRepository {

    List<CouponInfo> findAvailableCoupons();
}
