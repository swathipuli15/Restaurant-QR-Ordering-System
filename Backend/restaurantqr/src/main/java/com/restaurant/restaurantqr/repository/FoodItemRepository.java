package com.restaurant.restaurantqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem,Long>{

}
