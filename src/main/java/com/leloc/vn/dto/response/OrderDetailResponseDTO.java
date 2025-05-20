package com.leloc.vn.dto.response;

import com.leloc.vn.entity.Order;
import com.leloc.vn.entity.Product;
import lombok.Data;

@Data
public class OrderDetailResponseDTO {

    private Long orderDetailId;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double unitPrice;
}
