package com.example.purchaseorder.module.purchaseorder.gateway;

import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderDetailDTO;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderListDTO;
import com.example.purchaseorder.module.purchaseorder.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/order")
public class PurchaseOrderGateway {
    @Autowired
    private PurchaseOrderService service;

    @GetMapping
    public Page<PurchaseOrderListDTO> getOrderPage(@RequestParam("page") @Min(0) Integer page, @RequestParam("size") @Min(1) Integer size) {
        return service.getOrderPage(page, size);
    }

    @GetMapping("/{orderId}")
    public PurchaseOrderDetailDTO getOrderDetail(@PathVariable("orderId") Long orderId) {
        return service.getOrderDetail(orderId);
    }

}
