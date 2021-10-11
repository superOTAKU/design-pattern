package com.example.purchaseorder.module.purchaseorder.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseOrderListDTO {
    private Long id;
    private BigDecimal approvedLimit;
}
