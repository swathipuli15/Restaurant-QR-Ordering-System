package com.restaurant.restaurantqr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.restaurantqr.entity.RestaurantOrder;

public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder,Long>{
  List<RestaurantOrder> findAllByOrderByOrderTimeDesc();
  long countByOrderStatus(String orderStatus);
  @EntityGraph(attributePaths= {"orderItems",
		  "orderItems.foodItem",
		  "tableSession",
		  "tableSession.restaurantTable"})
  @Query("SELECT DISTINCT o FROM RestaurantOrder o")
  List<RestaurantOrder> findAllWithItems();
  List<RestaurantOrder> findByTableSession_SessionId(Long sessionId);
  
}
