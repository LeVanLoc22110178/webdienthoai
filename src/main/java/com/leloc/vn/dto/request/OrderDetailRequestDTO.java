package com.leloc.vn.dto.request;

import com.leloc.vn.entity.Order;
import com.leloc.vn.entity.Product;
import lombok.Data;


@Data
public class OrderDetailRequestDTO {

    private Long productId;
    private Integer quantity;
    private Double unitPrice;
}
