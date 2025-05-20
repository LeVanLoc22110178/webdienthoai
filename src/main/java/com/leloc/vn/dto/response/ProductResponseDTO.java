package com.leloc.vn.dto.response;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long productId;          // ID sản phẩm
    private String productName;      // Tên sản phẩm
    private String imageUrl;         // URL ảnh sản phẩm
    private String description; // Mô tả sản phẩm
    private Double price;     // Giá sản phẩm
    private Integer stockQuantity; // Số lượng trong kho
    private Long brandId;            // ID thương hiệu
    private String brandName;        // Tên thương hiệu
    private Long categoryId;         // ID loại sản phẩm
    private String releaseDate; // Ngày phát hành
    private String status;           // Trạng thái sản phẩm (Còn hàng, Hết hàng, Ngừng sản xuất)
    private String categoryName;     // Tên loại sản phẩm
}
