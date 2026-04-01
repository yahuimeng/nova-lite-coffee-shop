package com.nova.coffee.modules.promotion.infrastructure.persistence;

import com.nova.coffee.modules.promotion.domain.model.CouponInfo;
import com.nova.coffee.modules.promotion.domain.repository.PromotionRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 内存版营销仓储。
 */
@Repository
public class InMemoryPromotionRepository implements PromotionRepository {

    @Override
    public List<CouponInfo> findAvailableCoupons() {
        return List.of(
            new CouponInfo("LATTE-5", "拿铁立减券", new BigDecimal("5.00"), "AVAILABLE"),
            new CouponInfo("NEW-USER-10", "新用户首单券", new BigDecimal("10.00"), "AVAILABLE")
        );
    }
}
