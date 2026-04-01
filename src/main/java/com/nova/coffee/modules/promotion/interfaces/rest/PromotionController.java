package com.nova.coffee.modules.promotion.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.promotion.application.PromotionApplicationService;
import com.nova.coffee.modules.promotion.interfaces.dto.CouponResponse;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 营销接口。
 */
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionApplicationService promotionApplicationService;

    public PromotionController(PromotionApplicationService promotionApplicationService) {
        this.promotionApplicationService = promotionApplicationService;
    }

    @GetMapping("/coupons")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<List<CouponResponse>> listCoupons() {
        return Result.success(promotionApplicationService.listAvailableCoupons());
    }
}
