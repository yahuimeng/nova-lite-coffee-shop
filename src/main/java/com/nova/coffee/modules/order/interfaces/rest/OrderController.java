package com.nova.coffee.modules.order.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.order.application.OrderApplicationService;
import com.nova.coffee.modules.order.interfaces.dto.CreateOrderRequest;
import com.nova.coffee.modules.order.interfaces.dto.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单接口。
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return Result.success("下单成功", orderApplicationService.createOrder(request));
    }

    @GetMapping("/{orderNo}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<OrderResponse> getOrder(@PathVariable String orderNo) {
        return Result.success(orderApplicationService.getOrder(orderNo));
    }
}
