package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.event.OrderEvent;
import com.ecommerce.orderservice.model.Order;
import com.ecommerce.orderservice.model.OrderStatus;
import com.ecommerce.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private static final String TOPIC = "order-events";

    @Override
    @Transactional
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        publishOrderEvent(savedOrder, "Order created");
        return savedOrder;
    }

    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        publishOrderEvent(updatedOrder, "Order status updated");
        return updatedOrder;
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private void publishOrderEvent(Order order, String message) {
        OrderEvent event = new OrderEvent();
        event.setOrderId(order.getId());
        event.setCustomerId(order.getCustomerId());
        event.setStatus(order.getStatus());
        event.setMessage(message);
        
        kafkaTemplate.send(TOPIC, event);
    }
} 