package com.nova.coffee.modules.cart.application;

import com.nova.coffee.common.exception.BizException;
import com.nova.coffee.modules.cart.domain.repository.CartRepository;
import com.nova.coffee.modules.cart.interfaces.dto.CartItemResponse;
import com.nova.coffee.modules.cart.interfaces.dto.CartResponse;
import org.springframework.stereotype.Service;

/**
 * 购物车应用服务。
 */
@Service
public class CartApplicationService {

    private final CartRepository cartRepository;

    public CartApplicationService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartResponse getCurrentCart() {
        return cartRepository.findCurrentCart()
            .map(cart -> new CartResponse(
                cart.memberId(),
                cart.items().stream()
                    .map(item -> new CartItemResponse(item.productCode(), item.productName(), item.quantity(), item.unitPrice()))
                    .toList(),
                cart.totalAmount()
            ))
            .orElseThrow(() -> new BizException("购物车不存在"));
    }
}
