package com.leloc.vn.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDTO {
    private String email;  // Email của người dùng
    private String role;   // Vai trò của người dùng (ADMIN hoặc USER)
    private String token;  // Token JWT
}
