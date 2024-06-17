package vartz.microservices.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vartz.microservices.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, int quantity);
}
