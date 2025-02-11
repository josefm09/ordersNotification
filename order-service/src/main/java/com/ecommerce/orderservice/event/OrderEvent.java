package com.ecommerce.orderservice.event;

import lombok.Data;
import com.ecommerce.orderservice.model.OrderStatus;

@Data
public class OrderEvent {
    private Long orderId;
    private String customerId;
    private OrderStatus status;
    private String message;
} 