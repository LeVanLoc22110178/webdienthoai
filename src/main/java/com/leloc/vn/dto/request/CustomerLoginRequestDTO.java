package com.leloc.vn.dto.request;

import lombok.Data;

@Data
public class CustomerLoginRequestDTO {
    private String email;    // Email của người dùng
    private String password; // Mật khẩu của người dùng
}
