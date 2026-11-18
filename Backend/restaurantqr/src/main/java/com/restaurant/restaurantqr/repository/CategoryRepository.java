package com.restaurant.restaurantqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
