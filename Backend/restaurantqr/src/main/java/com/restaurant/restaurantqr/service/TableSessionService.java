package com.restaurant.restaurantqr.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.RestaurantTable;
import com.restaurant.restaurantqr.entity.TableSession;
import com.restaurant.restaurantqr.repository.RestaurantTableRepository;
import com.restaurant.restaurantqr.repository.TableSessionRepository;

@Service
public class TableSessionService {
	private final TableSessionRepository sessionRepository;
	private final RestaurantTableRepository tableRepository;
	public TableSessionService(TableSessionRepository sessionRepository,RestaurantTableRepository tableRepository) {

	this.sessionRepository=sessionRepository;
	this.tableRepository=tableRepository;

	}
	public TableSession startSession(String qrToken) {
		RestaurantTable table=tableRepository.findByQrToken(qrToken).orElseThrow(()->new RuntimeException("Invalid QR Code"));
		if("OCCUPIED".equalsIgnoreCase(table.getStatus())) {
//			throw new RuntimeException("Table is already occupied");
			return sessionRepository.findFirstByRestaurantTable_TableIdAndStatusIgnoreCaseOrderBySessionIdDesc(
					table.getTableId(),"ACTIVE").orElseThrow(()->new RuntimeException("Table is occupied but no Active session Exists"));		
		}
		TableSession session=new TableSession();
		session.setStartTime(LocalDateTime.now());
		session.setStatus("ACTIVE");
		session.setRestaurantTable(table);
		table.setStatus("OCCUPIED");
		tableRepository.save(table);
		return sessionRepository.save(session);
	}
	public TableSession closeSession(Long sessionId) {
		TableSession session=sessionRepository.findById(sessionId).orElseThrow(()->new RuntimeException("Session not found"));
		session.setStatus("CLOSED");
		session.setEndTime(LocalDateTime.now());
		RestaurantTable table=session.getRestaurantTable();
		table.setStatus("AVAILABLE");
		tableRepository.save(table);
		return sessionRepository.save(session);


	}
}
