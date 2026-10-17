package com.sushil.ecom_order_service.service;

import com.sushil.ecom_order_service.dtos.Inventory;
import com.sushil.ecom_order_service.exceptions.MyCustomRuntimeException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(String productId) {
        ResponseEntity<Inventory> response = restClient.get().uri("http://localhost:8081/inventory/{productId}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (((request, response1) -> {
                    throw new MyCustomRuntimeException(response1.getStatusCode(), response1.getHeaders());
                })))
                .toEntity(Inventory.class);
        updateInventory(response.getBody());
        return response.getBody() != null && response.getBody().getQuantity() > 0 ? "Order Placed Successfully" :
                "Prodiuct Out Of Stock";
//        return "IN STOCK".equals(response.getBody()) ? "order placed successfully" : "product out of stock";
//        return "order placed";
    }

    private void updateInventory(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity() - 1);
        restClient.post()
                .uri("http://localhost:8081/inventory")
                .body(inventory)
                .retrieve()
                .toBodilessEntity();
    }
}
