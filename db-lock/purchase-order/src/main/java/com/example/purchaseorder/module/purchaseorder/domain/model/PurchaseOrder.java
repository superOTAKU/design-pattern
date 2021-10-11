package com.example.purchaseorder.module.purchaseorder.domain.model;

import com.example.purchaseorder.exception.ApplicationException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal approvedLimit;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items;
    //version用于控制对order的并发
    @Version
    private Long version;

    public static PurchaseOrder of(BigDecimal approvedLimit) {
        PurchaseOrder order = new PurchaseOrder();
        order.setApprovedLimit(approvedLimit);
        return order;
    }

    //--------------------------domain logic--------------------------------------

    /**
     * 新增一个item，注意检查约束条件
     */
    public void addItem(PurchaseOrderItem newItem) {
        Optional<PurchaseOrderItem> itemOptional = items.stream().filter(i -> i.getPart().equals(newItem.getPart())).findAny();
        if (itemOptional.isPresent()) {
            throw new ApplicationException("duplicated part in an order.");
        }
        BigDecimal amount = new BigDecimal(0);
        for (var item : items) {
            amount = amount.add(item.calculateAmount());
        }
        amount = amount.add(newItem.calculateAmount());
        if (amount.compareTo(approvedLimit) > 0) {
            throw new ApplicationException("limit exceed.");
        }
        items.add(newItem);
    }

    public BigDecimal calculateAllItemAmount() {
        BigDecimal amount = new BigDecimal(0);
        for (var item : items) {
            amount = amount.add(item.calculateAmount());
        }
        return amount;
    }

    /**
     * 修改限制金额
     * @param newLimit 新的限制金额
     */
    public void updateLimit(BigDecimal newLimit) {
        if (newLimit.compareTo(calculateAllItemAmount()) < 0) {
            throw new ApplicationException("limit illegal!");
        }
        approvedLimit = newLimit;
    }

    //--------------------------getters and setters-------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getApprovedLimit() {
        return approvedLimit;
    }

    public void setApprovedLimit(BigDecimal approvedLimit) {
        this.approvedLimit = approvedLimit;
    }

    public List<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseOrderItem> items) {
        this.items = items;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseOrder)) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
