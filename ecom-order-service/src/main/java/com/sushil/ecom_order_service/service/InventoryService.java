package com.sushil.ecom_order_service.service;

import com.sushil.ecom_order_service.client.InventoryClient;
import com.sushil.ecom_order_service.dtos.Inventory;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
    @RateLimiter(name = "inventoryService", fallbackMethod = "fallbackMethod")

    @CircuitBreaker(name="inventoryServiceCircuitBreaker", fallbackMethod = "circuitBreakerFallbackMethod")
    public Inventory getInventory(Long productId) {
        return inventoryClient.getInventory(productId);
    }

    public Inventory fallbackMethod(Long productId, Throwable throwable) {
        System.out.println("fallback method call");
        return new Inventory(productId,0);
    }

    public Inventory circuitBreakerFallbackMethod(Long productId, Throwable throwable) {
        System.out.println(" circuit breaker fallback method call");
        return new Inventory(productId,0);
    }
}
