package com.leloc.vn.repository;

import com.leloc.vn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status); // Tìm đơn hàng theo trạng thái
    List<Order> findByCustomer_CustomerId(Long customerId);

}
