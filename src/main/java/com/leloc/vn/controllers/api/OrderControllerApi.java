package com.leloc.vn.controllers.api;


import com.leloc.vn.config.JwtAuthenticationContext;
import com.leloc.vn.dto.request.OrderDetailRequestDTO;
import com.leloc.vn.dto.response.OrderDetailResponseDTO;
import com.leloc.vn.dto.response.OrderResponseDTO;
import com.leloc.vn.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OrderControllerApi {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private JwtAuthenticationContext authContext;


    @PostMapping("/api/v1/create-order")
    public OrderResponseDTO createOrder(@RequestBody List<OrderDetailRequestDTO> orderRequestDTOs) {
        Long customerId = authContext.getCurrentUserId();
        Long orderId = orderService.createOrder(customerId);
        if (orderId != null) {
            orderService.createOrderDetail(orderRequestDTOs, orderId);
            return orderService.updateTotalPriceOrder(orderId);
        } else {
            return null;
        }
    }
    @GetMapping("/api/v1/get-all-orders/peding")
    public List<OrderResponseDTO> getAllOrdersPeding() {
        return orderService.getAllOrdersByStatus("pending"); // Truyền status vào để lọc đơn hàng
    }

    @GetMapping("/api/v1/get-all-orders/confirmed")
    public List<OrderResponseDTO> getAllOrdersConfirmed() {
        return orderService.getAllOrdersByStatus("confirmed"); // Truyền status vào để lọc đơn hàng
    }
    @PutMapping("/api/v1/update-order-status/{orderId}")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long orderId, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        return orderService.updateOrderStatus(orderId, status); // Cập nhật trạng thái đơn hàng
    }

    @GetMapping("/api/v1/get-orders-by-token")
    public List<OrderResponseDTO> getOrdersByToken(@RequestHeader("Authorization") String token) {
        try {
            // Lấy thông tin người dùng từ token
            Long userId = authContext.getCurrentUserId();

            if (userId == null) {
                throw new Exception("User not authenticated");
            }

            return orderService.getOrdersByUserToken(userId);
        } catch (Exception e) {
            // Xử lý lỗi nếu không lấy được đơn hàng
            return null;
        }
    }










}
