package com.nova.coffee.modules.product.application;

import com.nova.coffee.modules.product.domain.repository.ProductRepository;
import com.nova.coffee.modules.product.interfaces.dto.ProductResponse;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商品应用服务。
 */
@Service
public class ProductApplicationService {

    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> listAvailableProducts() {
        return productRepository.findAllAvailable().stream()
            .map(product -> new ProductResponse(product.productCode(), product.name(), product.price()))
            .toList();
    }
}
