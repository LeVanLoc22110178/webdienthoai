package com.leloc.vn.dto.request;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String productName;      // Tên sản phẩm
    private String description;      // Mô tả sản phẩm
    private Long brandId;            // ID thương hiệu
    private Long categoryId;         // ID loại sản phẩm
    private String imageUrl;         // URL ảnh sản phẩm
    private Double price;            // Giá sản phẩm (Đổi thành Double thay vì String)
    private Integer stockQuantity;   // Số lượng sản phẩm trong kho
    private String releaseDate;      // Ngày phát hành
    private String status;           // Trạng thái sản phẩm
}
