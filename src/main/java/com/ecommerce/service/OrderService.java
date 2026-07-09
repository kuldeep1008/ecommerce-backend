package com.ecommerce.service;

import com.ecommerce.dto.CreateOrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.dto.PagedResponse;
import com.ecommerce.entity.Order;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse createOrder(String userEmail, CreateOrderRequest request);
    OrderResponse getOrderById(String userEmail, Long orderId);
    PagedResponse<OrderResponse> getUserOrders(String userEmail, Pageable pageable);
    OrderResponse updateOrderStatus(Long orderId, Order.OrderStatus status);
    void cancelOrder(String userEmail, Long orderId);
}
