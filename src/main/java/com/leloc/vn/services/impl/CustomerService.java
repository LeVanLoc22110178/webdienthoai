package com.leloc.vn.services.impl;

import com.leloc.vn.config.JwtUtil;
import com.leloc.vn.dto.request.CustomerLoginRequestDTO;
import com.leloc.vn.dto.request.CustomerRegisterRequestDTO;
import com.leloc.vn.dto.response.CustomerResponseAdminDTO;
import com.leloc.vn.dto.response.CustomerResponseDTO;
import com.leloc.vn.entity.Customer;
import com.leloc.vn.mapper.CustomerMapper;
import com.leloc.vn.repository.CustomerRepository;
import com.leloc.vn.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder; // Tiêm PasswordEncoder

    // Đăng ký người dùng mới
    @Override
    public CustomerResponseDTO registerCustomer(CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        // Kiểm tra xem email đã tồn tại chưa
        if (customerRepository.existsByEmail(customerRegisterRequestDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Ánh xạ từ DTO sang Entity bằng CustomerMapper
        Customer customer = customerMapper.toEntity(customerRegisterRequestDTO);

        // Mã hóa mật khẩu trước khi lưu
        customer.setPassword(passwordEncoder.encode(customerRegisterRequestDTO.getPassword()));


        // Lưu vào cơ sở dữ liệu
        customer = customerRepository.save(customer);

        // Tạo JWT token với userId và email
        String token = jwtUtil.generateToken(
                customer.getEmail(),                    // username
                customer.getRole().name(),              // role
                customer.getCustomerId(),                       // userId
                customer.getEmail()                     // email
        );

        // Ánh xạ từ Entity sang DTO và thêm token
        CustomerResponseDTO customerResponseDTO = customerMapper.toResponseDTO(customer);
        customerResponseDTO.setToken(token);


        return customerResponseDTO;
    }

    // Xử lý login
    @Override
    public CustomerResponseDTO login(CustomerLoginRequestDTO customerLoginRequestDTO) {
        // Tìm khách hàng bằng email
        Customer customer = customerRepository.findByEmail(customerLoginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(customerLoginRequestDTO.getPassword(), customer.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Tạo token JWT với userId và email
        String token = jwtUtil.generateToken(
                customer.getEmail(),                    // username
                customer.getRole().name(),              // role
                customer.getCustomerId(),                       // userId
                customer.getEmail()                     // email
        );

        // Ánh xạ từ Customer entity sang CustomerResponseDTO
        CustomerResponseDTO customerResponseDTO = customerMapper.toResponseDTO(customer);
        customerResponseDTO.setToken(token); // Thêm token vào DTO

        return customerResponseDTO;
    }

    // Phương thức để lấy thông tin customer từ token JWT
    @Override
    public Customer getCustomerFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ phần "Bearer " từ token
        }

        // Kiểm tra token có hợp lệ không
        if (token != null && !jwtUtil.isTokenExpired(token)) {
            // Lấy userId từ token
            Long userId = jwtUtil.extractUserId(token);

            // Tìm customer theo userId
            return customerRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
        }

        throw new RuntimeException("Invalid or expired token");
    }



    @Override
    public List<CustomerResponseAdminDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseAdminDTO> customerResponseAdminDTOs = new ArrayList<>();

        // Map thủ công từ Customer sang CustomerResponseAdminDTO
        for (Customer customer : customers) {
            CustomerResponseAdminDTO customerResponseAdminDTO = new CustomerResponseAdminDTO();

            customerResponseAdminDTO.setCustomerId(customer.getCustomerId());
            customerResponseAdminDTO.setFirstName(customer.getFirstName());
            customerResponseAdminDTO.setLastName(customer.getLastName());
            customerResponseAdminDTO.setEmail(customer.getEmail());
            customerResponseAdminDTO.setPhone(customer.getPhone());
            customerResponseAdminDTO.setAddress(customer.getAddress());
            customerResponseAdminDTO.setCreatedAt(customer.getCreatedAt());
            customerResponseAdminDTO.setRole(customer.getRole().name()); // Enum role chuyển thành String

            customerResponseAdminDTOs.add(customerResponseAdminDTO);
        }

        return customerResponseAdminDTOs;
    }

    @Override
    public CustomerResponseAdminDTO addCustomer(CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        if (customerRepository.existsByEmail(customerRegisterRequestDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Customer customer = customerMapper.toEntity(customerRegisterRequestDTO);
        customer.setPassword(passwordEncoder.encode(customerRegisterRequestDTO.getPassword()));

        customer = customerRepository.save(customer);

        // Map thủ công từ Customer sang CustomerResponseAdminDTO
        CustomerResponseAdminDTO customerResponseAdminDTO = new CustomerResponseAdminDTO();
        customerResponseAdminDTO.setCustomerId(customer.getCustomerId());
        customerResponseAdminDTO.setFirstName(customer.getFirstName());
        customerResponseAdminDTO.setLastName(customer.getLastName());
        customerResponseAdminDTO.setEmail(customer.getEmail());
        customerResponseAdminDTO.setPhone(customer.getPhone());
        customerResponseAdminDTO.setAddress(customer.getAddress());
        customerResponseAdminDTO.setCreatedAt(customer.getCreatedAt());
        customerResponseAdminDTO.setRole(customer.getRole().name());

        return customerResponseAdminDTO;
    }


    @Override
    public CustomerResponseAdminDTO updateCustomer(Long customerId, CustomerRegisterRequestDTO customerRegisterRequestDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFirstName(customerRegisterRequestDTO.getFirstName());
        customer.setLastName(customerRegisterRequestDTO.getLastName());
        customer.setEmail(customerRegisterRequestDTO.getEmail());
        customer.setPhone(customerRegisterRequestDTO.getPhone());
        customer.setAddress(customerRegisterRequestDTO.getAddress());

        if (customerRegisterRequestDTO.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customerRegisterRequestDTO.getPassword()));
        }

        customer = customerRepository.save(customer);

        // Map thủ công từ Customer sang CustomerResponseAdminDTO
        CustomerResponseAdminDTO customerResponseAdminDTO = new CustomerResponseAdminDTO();
        customerResponseAdminDTO.setCustomerId(customer.getCustomerId());
        customerResponseAdminDTO.setFirstName(customer.getFirstName());
        customerResponseAdminDTO.setLastName(customer.getLastName());
        customerResponseAdminDTO.setEmail(customer.getEmail());
        customerResponseAdminDTO.setPhone(customer.getPhone());
        customerResponseAdminDTO.setAddress(customer.getAddress());
        customerResponseAdminDTO.setCreatedAt(customer.getCreatedAt());
        customerResponseAdminDTO.setRole(customer.getRole().name());

        return customerResponseAdminDTO;
    }


    @Override
    public void deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

}