package com.restaurant.restaurantqr.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_orders")
public class RestaurantOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	private LocalDateTime orderTime;
	private String orderStatus;
	private BigDecimal totalAmount;
	@ManyToOne
	@JoinColumn(name="session_Id")
	private TableSession tableSession;
	@OneToMany(mappedBy="restaurantOrder",cascade=CascadeType.ALL)
	private List<OrderItem> orderItems;
	public RestaurantOrder() {

	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId=orderId;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;

	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime=orderTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus=orderStatus;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount=totalAmount;
	}
	public TableSession getTableSession() {
		return tableSession;
	}
	public void setTableSession(TableSession tableSession) {
		this.tableSession=tableSession;
	}
	public List<OrderItem> getOrderItem(){
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems=orderItems;
	}

}
