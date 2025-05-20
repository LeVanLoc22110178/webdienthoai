package com.leloc.vn.services;

import com.leloc.vn.dto.request.CustomerLoginRequestDTO;
import com.leloc.vn.dto.request.CustomerRegisterRequestDTO;
import com.leloc.vn.dto.response.CustomerResponseAdminDTO;
import com.leloc.vn.dto.response.CustomerResponseDTO;
import com.leloc.vn.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public CustomerResponseDTO login(CustomerLoginRequestDTO customerLoginRequestDTO);
    public CustomerResponseDTO registerCustomer(CustomerRegisterRequestDTO customerRegisterRequestDTO);
    public Customer getCustomerFromToken(String token);
    public void deleteCustomer(Long customerId);
    public CustomerResponseAdminDTO updateCustomer(Long customerId, CustomerRegisterRequestDTO customerRegisterRequestDTO);
    public CustomerResponseAdminDTO addCustomer(CustomerRegisterRequestDTO customerRegisterRequestDTO) ;
    public List<CustomerResponseAdminDTO> getAllCustomers();
}
