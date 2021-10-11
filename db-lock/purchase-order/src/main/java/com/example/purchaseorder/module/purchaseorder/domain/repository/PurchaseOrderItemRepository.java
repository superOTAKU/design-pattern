package com.example.purchaseorder.module.purchaseorder.domain.repository;

import com.example.purchaseorder.module.purchaseorder.domain.model.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
}
