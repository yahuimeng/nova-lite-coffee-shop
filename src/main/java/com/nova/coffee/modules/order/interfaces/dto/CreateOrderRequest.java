package com.nova.coffee.modules.order.interfaces.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 创建订单请求。
 */
public record CreateOrderRequest(
    @NotBlank(message = "商品编码不能为空")
    String productCode,
    @Min(value = 1, message = "购买数量必须大于 0")
    int quantity
) {
}
