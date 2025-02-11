package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderStatus;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrderStatus(Long orderId, OrderStatus status);
    Order getOrder(Long orderId);
} 