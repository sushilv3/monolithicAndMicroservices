package com.sushil.ecom_inventory_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Inventory {
    @Id
    private Long productId;
    private Integer quantity;

    public Inventory() {

    }

    public Inventory(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
