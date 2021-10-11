package com.example.purchaseorder.module.purchaseorder.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PurchaseOrderDetailDTO {
    private Long id;
    private BigDecimal approvedLimit;
    private List<PurchaseOrderItemDTO> items;

    @Data
    public static class PurchaseOrderItemDTO {
        private Long id;
        private Long quantity;
        private BigDecimal price;
        private String partName;
    }
}
