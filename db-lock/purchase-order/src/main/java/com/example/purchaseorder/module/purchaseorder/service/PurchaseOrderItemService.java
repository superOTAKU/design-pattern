package com.example.purchaseorder.module.purchaseorder.service;

/**
 * 对item编辑的同时，要锁住PO，同时编辑item有如下规则：
 * 1.不能编辑part，只能编辑quantity
 * 2.part不能重复
 *
 */
public interface PurchaseOrderItemService {

    /**
     * 添加一个订单项
     * @param orderId 订单id
     * @param partId 采购物品id
     * @param quantity 采购物品数量
     * @return 订单项id
     */
    Long addItem(Long orderId, Long partId, Long quantity);

    /**
     * 修改订单项中物品数量
     */
    void updateItem(Long orderId, Long itemId, Long quantity);

    /**
     * 移除一个订单项
     */
    void removeItem(Long orderId, Long itemId);

}
