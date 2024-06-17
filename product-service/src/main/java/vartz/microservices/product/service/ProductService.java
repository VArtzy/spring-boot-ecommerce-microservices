package vartz.microservices.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vartz.microservices.product.dto.ProductRequest;
import vartz.microservices.product.dto.ProductResponse;
import vartz.microservices.product.model.Product;
import vartz.microservices.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
            .name(productRequest.name())
            .skuCode(productRequest.skuCode())
            .description(productRequest.description())
            .price(productRequest.price())
            .build();
        
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getSkuCode(), product.getDescription(), product.getPrice());
    }
}
