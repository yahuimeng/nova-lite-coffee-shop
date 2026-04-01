package com.nova.coffee.modules.order.domain.model;

import com.nova.coffee.common.exception.BizException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 咖啡订单聚合根。
 */
public class CoffeeOrder {

    private final String orderNo;
    private final String tenantId;
    private final String productCode;
    private final String productName;
    private final int quantity;
    private final BigDecimal totalAmount;
    private final LocalDateTime createdTime;
    private OrderStatus status;

    private CoffeeOrder(
        String orderNo,
        String tenantId,
        String productCode,
        String productName,
        int quantity,
        BigDecimal totalAmount,
        LocalDateTime createdTime,
        OrderStatus status
    ) {
        this.orderNo = orderNo;
        this.tenantId = tenantId;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.createdTime = createdTime;
        this.status = status;
    }

    public static CoffeeOrder create(
        String tenantId,
        String productCode,
        String productName,
        int quantity,
        BigDecimal unitPrice
    ) {
        if (Objects.isNull(unitPrice) || unitPrice.signum() <= 0) {
            throw new BizException("商品价格非法");
        }
        if (quantity <= 0) {
            throw new BizException("购买数量必须大于 0");
        }
        return new CoffeeOrder(
            "COF-" + UUID.randomUUID().toString().replace("-", "").substring(0, 12),
            tenantId,
            productCode,
            productName,
            quantity,
            unitPrice.multiply(BigDecimal.valueOf(quantity)),
            LocalDateTime.now(),
            OrderStatus.CREATED
        );
    }

    /**
     * 从持久化层重建聚合。
     */
    public static CoffeeOrder rebuild(
        String orderNo,
        String tenantId,
        String productCode,
        String productName,
        int quantity,
        BigDecimal totalAmount,
        LocalDateTime createdTime,
        OrderStatus status
    ) {
        return new CoffeeOrder(
            orderNo,
            tenantId,
            productCode,
            productName,
            quantity,
            totalAmount,
            createdTime,
            status
        );
    }

    public void markPaid() {
        if (status != OrderStatus.CREATED) {
            throw new BizException("只有已创建订单可以支付");
        }
        this.status = OrderStatus.PAID;
    }

    public void markPreparing() {
        if (status != OrderStatus.PAID) {
            throw new BizException("只有已支付订单才能开始制作");
        }
        this.status = OrderStatus.PREPARING;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
