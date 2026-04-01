package com.nova.coffee.modules.product.interfaces.rest;

import com.nova.coffee.common.result.Result;
import com.nova.coffee.modules.product.application.ProductApplicationService;
import com.nova.coffee.modules.product.interfaces.dto.ProductResponse;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品接口。
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<List<ProductResponse>> listProducts() {
        return Result.success(productApplicationService.listAvailableProducts());
    }
}
