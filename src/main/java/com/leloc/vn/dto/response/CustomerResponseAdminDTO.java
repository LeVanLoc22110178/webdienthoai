package com.leloc.vn.dto.response;

import lombok.Data;

@Data
public class CustomerResponseAdminDTO {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String createdAt;
    private String role; // Role dưới dạng String, ví dụ "ADMIN", "USER"
}
