package com.restaurant.restaurantqr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.RestaurantTable;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long>{
       boolean existsByTableNumber(Integer tableNumber);
       boolean existsByQrToken(String qrToken);
       Optional<RestaurantTable> findByQrToken(String qrToken);
       long countByStatus(String status);
}
