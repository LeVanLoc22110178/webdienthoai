package com.leloc.vn.mapper;

import com.leloc.vn.dto.request.CustomerLoginRequestDTO;
import com.leloc.vn.dto.request.CustomerRegisterRequestDTO;
import com.leloc.vn.dto.response.CustomerResponseDTO;
import com.leloc.vn.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Ánh xạ từ Customer entity sang CustomerResponseDTO
    @Mapping(source = "email", target = "email")
    @Mapping(source = "role", target = "role")
    CustomerResponseDTO toResponseDTO(Customer customer);

    // Ánh xạ từ CustomerLoginRequestDTO sang Customer entity
    // Không ánh xạ password từ DTO sang Entity ở đây, vì password sẽ được mã hóa trước khi lưu
    @Mapping(target = "password", ignore = true)  // Không ánh xạ mật khẩu
    @Mapping(source = "email", target = "email")
    Customer toEntity(CustomerLoginRequestDTO customerLoginRequestDTO);

    @Mapping(target = "password", ignore = true)  // Password sẽ được mã hóa sau đó
    @Mapping(target = "role", expression = "java(com.leloc.vn.entity.Customer.Role.valueOf(customerRegisterRequestDTO.getRole()))") // Chuyển role từ String sang Enum
    Customer toEntity(CustomerRegisterRequestDTO customerRegisterRequestDTO);
}
