package vartz.microservices.product.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, String skuCode, String description, BigDecimal price) {}
