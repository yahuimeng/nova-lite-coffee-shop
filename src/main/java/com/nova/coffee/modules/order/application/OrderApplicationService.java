package com.nova.coffee.modules.order.application;

import com.nova.coffee.common.exception.BizException;
import com.nova.coffee.infrastructure.tenant.TenantContext;
import com.nova.coffee.modules.order.domain.model.CoffeeOrder;
import com.nova.coffee.modules.order.domain.repository.OrderRepository;
import com.nova.coffee.modules.order.domain.service.OrderDomainService;
import com.nova.coffee.modules.order.interfaces.dto.CreateOrderRequest;
import com.nova.coffee.modules.order.interfaces.dto.OrderResponse;
import com.nova.coffee.modules.product.domain.model.CoffeeProduct;
import com.nova.coffee.modules.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * 订单应用服务。
 *
 * <p>这里负责串联“查商品 -> 创建订单 -> 支付 -> 进入制作中 -> 异步发消息”这条完整业务链。
 */
@Service
public class OrderApplicationService {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderAsyncNotifier orderAsyncNotifier;

    public OrderApplicationService(
        OrderDomainService orderDomainService,
        OrderRepository orderRepository,
        ProductRepository productRepository,
        OrderAsyncNotifier orderAsyncNotifier
    ) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderAsyncNotifier = orderAsyncNotifier;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        CoffeeProduct product = productRepository.findByCode(request.productCode())
            .filter(CoffeeProduct::available)
            .orElseThrow(() -> new BizException("商品不存在或当前不可售卖"));

        String tenantId = TenantContext.getTenantId();
        CoffeeOrder order = orderDomainService.createOrder(
            tenantId,
            product.productCode(),
            product.name(),
            request.quantity(),
            product.price()
        );
        order.markPaid();
        order.markPreparing();
        orderRepository.save(order);
        orderAsyncNotifier.notifyOrderPaid(order);
        return toResponse(order);
    }

    public OrderResponse getOrder(String orderNo) {
        CoffeeOrder order = orderRepository.findByOrderNo(orderNo)
            .orElseThrow(() -> new BizException("订单不存在"));
        return toResponse(order);
    }

    private OrderResponse toResponse(CoffeeOrder order) {
        return new OrderResponse(
            order.getOrderNo(),
            order.getProductCode(),
            order.getProductName(),
            order.getQuantity(),
            order.getTotalAmount(),
            order.getStatus().name(),
            order.getTenantId()
        );
    }
}
