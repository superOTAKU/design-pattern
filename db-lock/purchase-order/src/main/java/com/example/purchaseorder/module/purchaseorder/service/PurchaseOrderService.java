package com.example.purchaseorder.module.purchaseorder.service;

import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderDetailDTO;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderListDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface PurchaseOrderService {

    Long addOrder(BigDecimal approvedLimit);

    void deleteOrder(Long orderId);

    void updateOrder(Long orderId, BigDecimal approvedLimit);

    Page<PurchaseOrderListDTO> getOrderPage(Integer page, Integer size);

    PurchaseOrderDetailDTO getOrderDetail(Long orderId);

}
