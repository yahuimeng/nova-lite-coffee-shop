package com.nova.coffee.modules.cart.domain.repository;

import com.nova.coffee.modules.cart.domain.model.ShoppingCart;
import java.util.Optional;

/**
 * 购物车仓储接口。
 */
public interface CartRepository {

    Optional<ShoppingCart> findCurrentCart();
}
