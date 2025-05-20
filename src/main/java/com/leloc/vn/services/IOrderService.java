package com.leloc.vn.services;


import com.leloc.vn.dto.request.OrderDetailRequestDTO;
import com.leloc.vn.dto.request.OrderRequestDTO;
import com.leloc.vn.dto.response.OrderDetailResponseDTO;
import com.leloc.vn.dto.response.OrderResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IOrderService {

    public Long createOrder(Long customerId);
    public void createOrderDetail(List<OrderDetailRequestDTO> orderRequestDTOs, Long orderId);
    public OrderResponseDTO updateTotalPriceOrder(Long orderId);
    public List<OrderResponseDTO> getAllOrdersByStatus(String status);
    public OrderResponseDTO updateOrderStatus(Long orderId, String status);
    public List<OrderResponseDTO> getOrdersByUserToken(Long userId);

}
