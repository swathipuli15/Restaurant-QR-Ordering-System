package com.restaurant.restaurantqr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.entity.FoodItem;
import com.restaurant.restaurantqr.service.FoodItemService;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins={"http://localhost:5173", "http://10.88.19.221:5173"})
public class FoodItemController {

	private final FoodItemService foodItemService;
	public FoodItemController(FoodItemService foodItemService) {
		this.foodItemService=foodItemService;
	}
	@PostMapping
	public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
		return foodItemService.addFoodItem(foodItem);

	}
	@GetMapping
	public List<FoodItem> getAllFoodItems(){
		return foodItemService.getAllFoodItems();

	}
	@GetMapping("/{id}")
	public FoodItem getFoodItemById(@PathVariable Long id) {
		return foodItemService.getFoodItemById(id);
	}
	@PutMapping("/{id}")
	public FoodItem updateFoodItem(@PathVariable Long id,@RequestBody FoodItem foodItem) {
		return foodItemService.updateFoodItem(id, foodItem);
	}
	@DeleteMapping("/{id}")
	public String deleteFoodItem(@PathVariable Long id) {
		foodItemService.deleteFoodItem(id);
		return "Food Item Deleted Sucessfully";
	}
}
