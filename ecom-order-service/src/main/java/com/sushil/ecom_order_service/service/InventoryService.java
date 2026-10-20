package com.sushil.ecom_order_service.service;

import com.sushil.ecom_order_service.client.InventoryClient;
import com.sushil.ecom_order_service.dtos.Inventory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class InventoryService {
    private final InventoryClient inventoryClient;

    public InventoryService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @Retryable(retryFor = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public Inventory getInventory(Long productId) {
        return inventoryClient.getInventory(productId);
    }
}
