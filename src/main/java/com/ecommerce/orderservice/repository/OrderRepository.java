package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Spring Data JPA will automatically implement basic CRUD operations
    // Custom queries can be added here if needed

    // Example of a custom query method (if needed in the future):
    // List<Order> findByCustomerIdAndStatus(String customerId, OrderStatus status);
}