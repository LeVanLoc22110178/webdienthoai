package com.leloc.vn.dto.request;

import lombok.Data;

@Data
public class CustomerRegisterRequestDTO {

    private String firstName; // Tên
    private String lastName;  // Họ
    private String email;     // Email
    private String phone;     // Số điện thoại
    private String address;   // Địa chỉ
    private String password;  // Mật khẩu
    private String createdAt; // Ngày tạo

    // Thêm role mặc định là USER
    private String role = "USER";  // Mặc định là "USER", có thể là "ADMIN" nếu cần
}
