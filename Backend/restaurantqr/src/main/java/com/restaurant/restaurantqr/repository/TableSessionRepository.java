package com.restaurant.restaurantqr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.TableSession;

public interface TableSessionRepository extends JpaRepository<TableSession,Long>{
 Optional<TableSession> findFirstByRestaurantTable_TableIdAndStatusIgnoreCaseOrderBySessionIdDesc(Long tableId,String status);
}
