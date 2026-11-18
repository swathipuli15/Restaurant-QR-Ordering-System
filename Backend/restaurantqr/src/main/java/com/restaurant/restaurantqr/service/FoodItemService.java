package com.restaurant.restaurantqr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.FoodItem;
import com.restaurant.restaurantqr.repository.FoodItemRepository;

@Service
public class FoodItemService {
	private final FoodItemRepository foodItemRepository;
	public FoodItemService(FoodItemRepository foodItemRepository) {
		this.foodItemRepository=foodItemRepository;
	}
	// add foodItems
	public FoodItem addFoodItem(FoodItem foodItem) {
		return foodItemRepository.save(foodItem);

	}
	//get all food Items
	public List<FoodItem> getAllFoodItems(){
		return foodItemRepository.findAll();
	}
	//get food Item by id
	public FoodItem getFoodItemById(Long id) {
		return foodItemRepository.findById(id).orElse(null);
	}
	//update food items
	public FoodItem updateFoodItem(Long id,FoodItem foodItem) {
		FoodItem existingFood=foodItemRepository.findById(id).orElse(null);
		if(existingFood !=null) {
			existingFood.setFoodName(foodItem.getFoodName());
			existingFood.setDescription(foodItem.getDescription());
			existingFood.setPrice(foodItem.getPrice());
			existingFood.setAvailability(foodItem.isAvailability());
			existingFood.setCategory(foodItem.getCategory());
			return foodItemRepository.save(existingFood);
		}
		return null;
	}
	//delete food Items
	public void deleteFoodItem(Long id) {
		foodItemRepository.deleteById(id);
	}


}
