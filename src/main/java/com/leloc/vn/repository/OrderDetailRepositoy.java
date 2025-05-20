package com.leloc.vn.repository;

import com.leloc.vn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepositoy extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrder_orderId(Long orderId);

}
