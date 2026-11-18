package com.restaurant.restaurantqr.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemId;
	private Integer quantity;
	private BigDecimal price;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="order_id")
	private RestaurantOrder restaurantOrder;
	@ManyToOne
	@JoinColumn(name="food_id")
	private FoodItem foodItem;
	
	public OrderItem() {

	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId=itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity=quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price=price;
	}
	public RestaurantOrder getRestaurantOrder() {
		return restaurantOrder;
	}
	public void setRestaurantOrder(RestaurantOrder restaurantOrder) {
		this.restaurantOrder=restaurantOrder;
	}
	public FoodItem getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(FoodItem foodItem) {
		this.foodItem=foodItem;
	}

}
