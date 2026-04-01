package com.nova.coffee.modules.cart.infrastructure.persistence;

import com.nova.coffee.modules.cart.domain.model.CartItem;
import com.nova.coffee.modules.cart.domain.model.ShoppingCart;
import com.nova.coffee.modules.cart.domain.repository.CartRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 内存版购物车仓储。
 */
@Repository
public class InMemoryCartRepository implements CartRepository {

    @Override
    public Optional<ShoppingCart> findCurrentCart() {
        return Optional.of(new ShoppingCart(
            1001L,
            List.of(
                new CartItem("LATTE", "拿铁", 2, new BigDecimal("28.00")),
                new CartItem("MOCHA", "摩卡", 1, new BigDecimal("32.00"))
            )
        ));
    }
}
