package com.leloc.vn.mapper;

import com.leloc.vn.dto.request.ProductRequestDTO;
import com.leloc.vn.dto.response.ProductResponseDTO;
import com.leloc.vn.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.categoryId", target = "categoryId")  // ánh xạ categoryId từ Product
    @Mapping(source = "category.categoryName", target = "categoryName")  // ánh xạ categoryName từ Product
    @Mapping(source = "brand.brandId", target = "brandId")  // ánh xạ brandId từ Product
    @Mapping(source = "brand.brandName", target = "brandName")  // ánh xạ brandName từ Product
    ProductResponseDTO toResponseDTO(Product product); // ánh xạ từ Product sang ProductResponseDTO

    @Mapping(source = "categoryId", target = "category.categoryId")  // ánh xạ categoryId vào Product
    @Mapping(source = "brandId", target = "brand.brandId")  // ánh xạ brandId vào Product
    Product toEntity(ProductRequestDTO productRequestDTO);  // ánh xạ từ ProductRequestDTO sang Product
}