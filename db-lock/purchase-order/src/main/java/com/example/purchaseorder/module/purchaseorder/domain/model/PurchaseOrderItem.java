package com.example.purchaseorder.module.purchaseorder.domain.model;

import com.example.purchaseorder.exception.ApplicationException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private PurchaseOrder order;
    @ManyToOne
    @JoinColumn(name="part_id", nullable = false)
    private Part part;

    //----------------factory method-------------------

    public static PurchaseOrderItem of(PurchaseOrder order, Part part, Long quantity) {
        PurchaseOrderItem item = new PurchaseOrderItem();
        item.setOrder(order);
        item.setPart(part);
        item.setQuantity(quantity);
        item.setPrice(part.getPrice());
        return item;
    }

    //------------------domain logic--------------------

    public BigDecimal calculateAmount() {
        return price.multiply(new BigDecimal(quantity));
    }

    public void updateQuantity(Long newQuantity) {
        price = part.getPrice();
        //检查修改quantity后是否满足约束
        BigDecimal amount = new BigDecimal(0);
        for (var item : order.getItems()) {
            if (!Objects.equals(id, item.getId())) {
                amount = amount.add(item.calculateAmount());
            } else {
                amount = amount.add(new BigDecimal(newQuantity).multiply(price));
            }
        }
        if (amount.compareTo(order.getApprovedLimit()) > 0) {
            throw new ApplicationException("order limit exceed.");
        }
        quantity = newQuantity;
    }

    //----------------------------------getters and setters--------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrderItem)) return false;
        PurchaseOrderItem item = (PurchaseOrderItem) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
