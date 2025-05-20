package com.leloc.vn.dto.response;


import com.leloc.vn.entity.Customer;
import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private Customer customer;
    private Date orderDate;
    private String status;
    private Double totalAmount;
}
