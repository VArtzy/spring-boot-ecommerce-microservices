package vartz.microservices.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import vartz.microservices.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {}
