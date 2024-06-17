package vartz.microservices.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vartz.microservices.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
