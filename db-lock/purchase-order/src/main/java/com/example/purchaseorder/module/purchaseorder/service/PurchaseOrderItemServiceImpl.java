package com.example.purchaseorder.module.purchaseorder.service;

import com.example.purchaseorder.module.purchaseorder.domain.model.Part;
import com.example.purchaseorder.module.purchaseorder.domain.model.PurchaseOrder;
import com.example.purchaseorder.module.purchaseorder.domain.model.PurchaseOrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Long addItem(Long orderId, Long partId, Long quantity) {
        PurchaseOrder order = entityManager.find(PurchaseOrder.class, orderId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        Part part = entityManager.find(Part.class, partId);
        PurchaseOrderItem item = PurchaseOrderItem.of(order, part, quantity);
        order.addItem(item);
        entityManager.persist(item);
        return item.getId();
    }

    @Transactional
    @Override
    public void updateItem(Long orderId, Long itemId, Long quantity) {
        entityManager.find(PurchaseOrder.class, orderId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        PurchaseOrderItem item = entityManager.find(PurchaseOrderItem.class, itemId);
        item.updateQuantity(quantity);
    }

    @Override
    public void removeItem(Long orderId, Long itemId) {
        entityManager.find(PurchaseOrder.class, orderId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        PurchaseOrderItem item = entityManager.find(PurchaseOrderItem.class, itemId);
        entityManager.remove(item);
    }

}
