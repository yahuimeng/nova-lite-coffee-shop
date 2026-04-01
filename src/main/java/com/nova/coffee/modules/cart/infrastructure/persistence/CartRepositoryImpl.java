package com.nova.coffee.modules.cart.infrastructure.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.nova.coffee.modules.cart.domain.model.CartItem;
import com.nova.coffee.modules.cart.domain.model.ShoppingCart;
import com.nova.coffee.modules.cart.domain.repository.CartRepository;
import com.nova.coffee.modules.cart.infrastructure.persistence.dataobject.CartItemEntity;
import com.nova.coffee.modules.cart.infrastructure.persistence.mapper.CartItemMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * 购物车仓储实现。
 *
 * <p>优先读取 MySQL；若本地数据库尚未初始化，则回退到演示数据。
 */
@Repository
public class CartRepositoryImpl implements CartRepository {

    private static final Long DEMO_MEMBER_ID = 1001L;

    private final CartItemMapper cartItemMapper;

    public CartRepositoryImpl(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public Optional<ShoppingCart> findCurrentCart() {
        try {
            List<CartItemEntity> entities = cartItemMapper.selectList(
                new LambdaQueryWrapper<CartItemEntity>()
                    .eq(CartItemEntity::getMemberId, DEMO_MEMBER_ID)
            );
            if (entities != null && !entities.isEmpty()) {
                return Optional.of(new ShoppingCart(
                    DEMO_MEMBER_ID,
                    entities.stream().map(this::toDomain).toList()
                ));
            }
        } catch (Exception exception) {
            // 数据库未准备好时，回退到演示数据。
        }

        return Optional.of(new ShoppingCart(
            DEMO_MEMBER_ID,
            List.of(
                new CartItem("LATTE", "拿铁", 2, new BigDecimal("28.00")),
                new CartItem("MOCHA", "摩卡", 1, new BigDecimal("32.00"))
            )
        ));
    }

    private CartItem toDomain(CartItemEntity entity) {
        return new CartItem(
            entity.getProductCode(),
            entity.getProductName(),
            entity.getQuantity(),
            entity.getUnitPrice()
        );
    }
}
