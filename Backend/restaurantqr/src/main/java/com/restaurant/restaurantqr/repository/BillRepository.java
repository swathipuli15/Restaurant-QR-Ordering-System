package com.restaurant.restaurantqr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurantqr.entity.Bill;

public interface BillRepository extends JpaRepository<Bill,Long>{
	Optional<Bill> findByTableSession_SessionId(Long sessionId);

}
