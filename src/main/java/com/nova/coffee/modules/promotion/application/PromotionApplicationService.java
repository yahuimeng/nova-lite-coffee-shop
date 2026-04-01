package com.nova.coffee.modules.promotion.application;

import com.nova.coffee.modules.promotion.domain.repository.PromotionRepository;
import com.nova.coffee.modules.promotion.interfaces.dto.CouponResponse;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 营销应用服务。
 */
@Service
public class PromotionApplicationService {

    private final PromotionRepository promotionRepository;

    public PromotionApplicationService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<CouponResponse> listAvailableCoupons() {
        return promotionRepository.findAvailableCoupons().stream()
            .map(coupon -> new CouponResponse(
                coupon.couponCode(),
                coupon.title(),
                coupon.discountAmount(),
                coupon.status()
            ))
            .toList();
    }
}
