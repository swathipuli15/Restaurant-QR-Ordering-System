package com.restaurant.restaurantqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
