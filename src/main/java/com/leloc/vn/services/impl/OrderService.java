package com.leloc.vn.services.impl;


import com.leloc.vn.dto.request.OrderDetailRequestDTO;
import com.leloc.vn.dto.request.OrderRequestDTO;
import com.leloc.vn.dto.response.OrderDetailResponseDTO;
import com.leloc.vn.dto.response.OrderResponseDTO;
import com.leloc.vn.entity.Customer;
import com.leloc.vn.entity.Order;
import com.leloc.vn.entity.OrderDetail;
import com.leloc.vn.mapper.OrderDetailMapper;
import com.leloc.vn.mapper.OrderMapper;
import com.leloc.vn.repository.CustomerRepository;
import com.leloc.vn.repository.OrderDetailRepositoy;
import com.leloc.vn.repository.OrderRepository;
import com.leloc.vn.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepositoy orderDetailRepositoy;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Long createOrder(Long customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).orElse(null);
            Order order = new Order();
            order.setCustomer(customer);
            order.setStatus("pending");
            order.setTotalAmount(200.0); // Set a default value for totalAmount
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            order.setOrderDate(currentDate);

            orderRepository.save(order);
            return order.getOrderId();
        } catch (Exception e) {

            return null;
        }
    }


    @Override
    public void createOrderDetail(List<OrderDetailRequestDTO> orderRequestDTOs, Long orderId) {

        Order order = orderRepository.findById(orderId).orElse(null);


        // Loop through all order details and create them using the mapper
        for (OrderDetailRequestDTO orderRequestDTO : orderRequestDTOs) {
            OrderDetail orderDetail = orderDetailMapper.toEntity(orderRequestDTO);
            orderDetail.setOrder(order);
            orderDetailRepositoy.save(orderDetail);
        }
        updateTotalPriceOrder(orderId);


    }

    @Override
    public OrderResponseDTO updateTotalPriceOrder(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order == null) {
                return null;
            }

            List<OrderDetail> orderDetails = orderDetailRepositoy.findByOrder_orderId(orderId);
            double totalPrice = 0.0;
            for (OrderDetail orderDetail : orderDetails) {
                totalPrice += orderDetail.getUnitPrice() * orderDetail.getQuantity();
            }

            // Update total price in order
            order.setTotalAmount(totalPrice);
            orderRepository.save(order);

            return orderMapper.toResponseDTO(order);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByStatus(String status) {
        try {
            // Tìm tất cả đơn hàng theo trạng thái
            List<Order> orders = orderRepository.findByStatus(status);

            // Tạo danh sách OrderResponseDTO
            List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();

            // Map thủ công từ Order sang OrderResponseDTO
            for (Order order : orders) {
                OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                orderResponseDTO.setOrderId(order.getOrderId());
                orderResponseDTO.setCustomer(order.getCustomer());
                orderResponseDTO.setOrderDate(order.getOrderDate());
                orderResponseDTO.setStatus(order.getStatus());
                orderResponseDTO.setTotalAmount(order.getTotalAmount());

                // Thêm vào danh sách kết quả
                orderResponseDTOs.add(orderResponseDTO);
            }

            return orderResponseDTOs;
        } catch (Exception e) {
            // Xử lý lỗi
            return null;
        }
    }

    @Override
    public OrderResponseDTO updateOrderStatus(Long orderId, String status) {
        try {
            Order order = orderRepository.findById(orderId).orElse(null);
            if (order == null) {
                return null; // Nếu không tìm thấy đơn hàng
            }

            // Cập nhật trạng thái của đơn hàng
            order.setStatus(status);
            orderRepository.save(order);

            return orderMapper.toResponseDTO(order); // Trả về đơn hàng đã cập nhật
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUserToken(Long userId) {
        try {
            // Lấy danh sách đơn hàng theo userId
            List<Order> orders = orderRepository.findByCustomer_CustomerId(userId);

            // Tạo danh sách OrderResponseDTO
            List<OrderResponseDTO> orderResponseDTOs = new ArrayList<>();

            // Map thủ công từ Order sang OrderResponseDTO
            for (Order order : orders) {
                OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
                orderResponseDTO.setOrderId(order.getOrderId());
                orderResponseDTO.setCustomer(order.getCustomer());
                orderResponseDTO.setOrderDate(order.getOrderDate());
                orderResponseDTO.setStatus(order.getStatus());
                orderResponseDTO.setTotalAmount(order.getTotalAmount());

                // Thêm vào danh sách kết quả
                orderResponseDTOs.add(orderResponseDTO);
            }

            return orderResponseDTOs;
        } catch (Exception e) {
            return null;
        }
    }

}




