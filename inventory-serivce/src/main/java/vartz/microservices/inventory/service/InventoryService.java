package vartz.microservices.inventory.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vartz.microservices.inventory.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public boolean isInStock(String skuCode, int quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
