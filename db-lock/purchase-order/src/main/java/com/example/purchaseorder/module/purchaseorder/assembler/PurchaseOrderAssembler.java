package com.example.purchaseorder.module.purchaseorder.assembler;

import com.example.purchaseorder.module.purchaseorder.domain.model.PurchaseOrder;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderDetailDTO;
import com.example.purchaseorder.module.purchaseorder.dto.PurchaseOrderListDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderAssembler {

    public static PurchaseOrderListDTO toListDTO(PurchaseOrder po) {
        PurchaseOrderListDTO dto = new PurchaseOrderListDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    public static PurchaseOrderDetailDTO toDetailDTO(PurchaseOrder po) {
        PurchaseOrderDetailDTO dto = new PurchaseOrderDetailDTO();
        dto.setId(po.getId());
        dto.setApprovedLimit(po.getApprovedLimit());
        List<PurchaseOrderDetailDTO.PurchaseOrderItemDTO> itemDTOList = new ArrayList<>();
        for (var item : po.getItems()) {
            PurchaseOrderDetailDTO.PurchaseOrderItemDTO itemDTO = new PurchaseOrderDetailDTO.PurchaseOrderItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setPartName(item.getPart().getName());
            itemDTOList.add(itemDTO);
        }
        dto.setItems(itemDTOList);
        return dto;
    }

}
