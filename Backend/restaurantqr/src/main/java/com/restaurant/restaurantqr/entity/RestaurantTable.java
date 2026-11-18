package com.restaurant.restaurantqr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant_tables")
public class RestaurantTable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long tableId;
	@Column(unique=true)
	private Integer tableNumber;
	@Column(unique=true)
	private String qrToken;
	private String status;
	public RestaurantTable() {

	}
	public RestaurantTable(Long tableId,Integer tableNumber,String qrToken,String status) {
		this.tableId=tableId;
		this.tableNumber=tableNumber;
		this.qrToken=qrToken;
		this.status=status;
	}
	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId=tableId;
	}
	public Integer getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(Integer tableNumber) {
		this.tableNumber=tableNumber;
	}
	public String getQrToken() {
		return qrToken;
	}
	public void setQrToken(String qrToken) {
		this.qrToken=qrToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}




}
