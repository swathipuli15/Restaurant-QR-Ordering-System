package com.restaurant.restaurantqr.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TableSession {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sessionId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;

	@ManyToOne
	@JoinColumn(name="table_id")
	private RestaurantTable restaurantTable;

	public TableSession() {

	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId=sessionId;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime=startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime=endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public RestaurantTable getRestaurantTable() {
		return restaurantTable;
	}
	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable=restaurantTable;
	}


}
