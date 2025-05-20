package com.leloc.vn.controllers.api;

import com.leloc.vn.dto.request.CustomerRegisterRequestDTO;
import com.leloc.vn.dto.response.CustomerResponseAdminDTO;
import com.leloc.vn.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CustomerAdminApi {

    @Autowired
    private ICustomerService customerService;

    // Thêm khách hàng
    @PostMapping("/api/v1/add-customer")
    public CustomerResponseAdminDTO addCustomer(@RequestBody CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        return customerService.addCustomer(customerRegisterRequestDTO);
    }

    // Lấy tất cả khách hàng
    @GetMapping("/api/v1/get-all-customers")
    public List<CustomerResponseAdminDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Cập nhật thông tin khách hàng
    @PutMapping("/api/v1/update-customer/{customerId}")
    public CustomerResponseAdminDTO updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        return customerService.updateCustomer(customerId, customerRegisterRequestDTO);
    }

    // Xóa khách hàng
    @DeleteMapping("/api/v1/delete-customer/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }
}
