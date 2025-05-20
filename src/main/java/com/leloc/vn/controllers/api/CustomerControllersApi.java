package com.leloc.vn.controllers.api;

import com.leloc.vn.config.JwtAuthenticationContext;
import com.leloc.vn.dto.request.CustomerLoginRequestDTO;
import com.leloc.vn.dto.request.CustomerRegisterRequestDTO;
import com.leloc.vn.dto.response.CustomerResponseDTO;
import com.leloc.vn.entity.Customer;
import com.leloc.vn.repository.CustomerRepository;
import com.leloc.vn.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CustomerControllersApi {
    @Autowired
    private ICustomerService customerService;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtAuthenticationContext authContext;
    // Đăng nhập người dùng
    @PostMapping("/login")
    public CustomerResponseDTO login(@RequestBody CustomerLoginRequestDTO customerLoginRequestDTO) {
        return customerService.login(customerLoginRequestDTO);
    }

    // Đăng ký người dùng mới
    @PostMapping("/register")
    public CustomerResponseDTO register(@RequestBody CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        return customerService.registerCustomer(customerRegisterRequestDTO);
    }


    @GetMapping("/get-customer")
    public ResponseEntity<?> getCurrentUser() {
        try {
            // Lấy thông tin người dùng từ SecurityContext
            Long userId = authContext.getCurrentUserId();
            String email = authContext.getCurrentUserEmail();
            String role = authContext.getCurrentUserRole();

            if (userId == null || email == null || role == null) {
                return ResponseEntity.status(401).body("User not authenticated");
            }

            // Tạo một response chứa thông tin người dùng
            Map<String, Object> response = new HashMap<>();
            response.put("userId", userId);
            response.put("email", email);
            response.put("role", role);


            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching user information");
        }
    }

    @GetMapping("/get-user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            // Lấy thông tin người dùng từ token
            Customer customer = customerService.getCustomerFromToken(token);

            // Tạo response trả về thông tin người dùng
            Map<String, Object> response = new HashMap<>();
            response.put("userId", customer.getCustomerId());
            response.put("email", customer.getEmail());
            response.put("role", customer.getRole());
            response.put("firstName", customer.getFirstName());
            response.put("lastName", customer.getLastName());
            response.put("phone", customer.getPhone());
            response.put("address", customer.getAddress());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching user information: " + e.getMessage());
        }
    }


    @PutMapping("/update-address")
    public ResponseEntity<?> updateAddress(@RequestHeader("Authorization") String token, @RequestBody String newAddress) {
        try {
            // Lấy thông tin người dùng từ token
            Customer customer = customerService.getCustomerFromToken(token);

            // Cập nhật địa chỉ
            customer.setAddress(newAddress);

            // Lưu vào cơ sở dữ liệu
            customerRepository.save(customer);

            return ResponseEntity.ok("Address updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while updating address: " + e.getMessage());
        }
    }


        @PutMapping("/update-phone")
        public ResponseEntity<?> updatePhone(@RequestHeader("Authorization") String token, @RequestBody String newPhone) {
            try {
                // Lấy thông tin người dùng từ token
                Customer customer = customerService.getCustomerFromToken(token);

                // Cập nhật số điện thoại
                customer.setPhone(newPhone);

                // Lưu vào cơ sở dữ liệu
                customerRepository.save(customer);

                return ResponseEntity.ok("Phone updated successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("An error occurred while updating phone: " + e.getMessage());
            }
        }



    }


