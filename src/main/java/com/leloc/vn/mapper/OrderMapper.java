package com.leloc.vn.mapper;


import com.leloc.vn.dto.request.OrderRequestDTO;
import com.leloc.vn.dto.response.OrderResponseDTO;
import com.leloc.vn.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(source = "customerId", target = "customer.customerId") // ánh xạ customerId từ Order
    Order toEntity(OrderRequestDTO orderRequestDTO); // ánh xạ từ OrderRequestDTO sang Order entit
    OrderResponseDTO toResponseDTO(Order order); // ánh xạ từ Order sang OrderResponseDTO
}
