package com.leloc.vn.repository;

import com.leloc.vn.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email); // Kiểm tra xem email đã tồn tại chưa
    Optional<Customer> findByEmail(String email); // Trả về Optional<Customer>
}
