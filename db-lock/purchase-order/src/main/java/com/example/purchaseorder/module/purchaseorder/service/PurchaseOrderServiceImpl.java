package com.example.purchaseorder.module.purchaseorder.service;

import com.example.purchaseorder.module.purchaseorder.assembler.PurchaseOrderAssembler;
import com.example.purchaseorder.module.purchaseorder.domain.model.PurchaseOrder;
import com.example.purchaseorder.module.purchaseorder.domain.repository.PurchaseOrderRepository;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderDetailDTO;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PurchaseOrderRepository repository;

    @Transactional
    @Override
    public Long addOrder(BigDecimal approvedLimit) {
        PurchaseOrder order = PurchaseOrder.of(approvedLimit);
        entityManager.persist(order);
        return order.getId();
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        PurchaseOrder order = entityManager.find(PurchaseOrder.class, orderId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        entityManager.remove(order);
    }

    @Transactional
    @Override
    public void updateOrder(Long orderId, BigDecimal approvedLimit) {
        PurchaseOrder order = entityManager.find(PurchaseOrder.class, orderId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        order.updateLimit(approvedLimit);
    }

    @Override
    public Page<PurchaseOrderListDTO> getOrderPage(Integer page, Integer size) {
        Page<PurchaseOrder> orders = repository.findAll(PageRequest.of(page, size));
        return orders.map(PurchaseOrderAssembler::toListDTO);
    }

    @Transactional
    @Override
    public PurchaseOrderDetailDTO getOrderDetail(Long orderId) {
        PurchaseOrder po = entityManager.find(PurchaseOrder.class, orderId);
        return PurchaseOrderAssembler.toDetailDTO(po);
    }

}
