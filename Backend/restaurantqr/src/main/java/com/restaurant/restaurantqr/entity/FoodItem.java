package com.restaurant.restaurantqr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FoodItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long foodId;
	private String foodName;
	private String description;
	private double price;
	private boolean availability;
	private String imgUrl;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	public FoodItem() {

	}
	public FoodItem(Long foodId,String foodName,String description,double price,boolean availability,Category category) {
		this.foodId=foodId;
		this.foodName=foodName;
		this.description=description;
		this.price=price;
		this.availability=availability;
		this.category=category;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId=foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName=foodName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price=price;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability=availability;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category=category;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


}
