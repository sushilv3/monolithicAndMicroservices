package com.sushil.ecom_inventory_service.controller;

import com.sushil.ecom_inventory_service.entity.Inventory;
import com.sushil.ecom_inventory_service.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public Inventory checkInventory(@PathVariable Long productId) {
//        return productId.equals("1") ? "IN STOCK" : "OUT STOCK";
    return inventoryService.checkStock(productId);
    }

    @PostMapping
    public String addProduct(@RequestBody Inventory inventory){
        return  inventoryService.addProduct(inventory);
    }
    @PutMapping
    public String updateProduct(@RequestBody Inventory inventory){
        return inventoryService.updateProduct(inventory);
    }

}
