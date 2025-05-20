package com.leloc.vn.mapper;

import com.leloc.vn.dto.request.OrderDetailRequestDTO;
import com.leloc.vn.dto.response.OrderDetailResponseDTO;
import com.leloc.vn.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "productId", target = "product.productId")  // ánh xạ productId vào trường product trong OrderDetail
    OrderDetail toEntity(OrderDetailRequestDTO orderDetailRequestDTO);

    // Ánh xạ từ OrderDetail entity sang OrderDetailResponseDTO
    OrderDetailResponseDTO toResponseDTO(OrderDetail orderDetail);
}
