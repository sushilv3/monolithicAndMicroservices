package com.sushil.ecom_inventory_service.service;

import com.sushil.ecom_inventory_service.entity.Inventory;
import com.sushil.ecom_inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory checkStock(Long productId) {
        Optional<Inventory> inv = inventoryRepository.findById(productId);
        return inv.get();
    }

    public String addProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Product Added";
    }

    public String updateProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Product Updated";
    }
}
