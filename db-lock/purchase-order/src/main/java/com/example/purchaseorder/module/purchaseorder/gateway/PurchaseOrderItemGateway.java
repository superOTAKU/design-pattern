package com.example.purchaseorder.module.purchaseorder.gateway;

import com.example.purchaseorder.module.purchaseorder.service.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Validated
@RequestMapping("/order/{orderId}/item")
@RestController
public class PurchaseOrderItemGateway {
    @Autowired
    private PurchaseOrderItemService service;

    @PostMapping
    public Long addItem(@PathVariable("orderId") Long orderId, @RequestParam("partId") Long partId, @RequestParam("quantity") @Min(1) Long quantity) {
        return service.addItem(orderId, partId, quantity);
    }

    @PutMapping("/{itemId}")
    public void updateItem(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId, @RequestParam("quantity") @Min(1) Long quantity) {
        service.updateItem(orderId, itemId, quantity);
    }

}
