package com.nova.coffee.modules.cart.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.cart.application.CartApplicationService;
import com.nova.coffee.modules.cart.interfaces.dto.CartResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车接口。
 */
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartApplicationService cartApplicationService;

    public CartController(CartApplicationService cartApplicationService) {
        this.cartApplicationService = cartApplicationService;
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<CartResponse> currentCart() {
        return Result.success(cartApplicationService.getCurrentCart());
    }
}
