package com.restaurant.restaurantqr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.dto.OrderRequest;
import com.restaurant.restaurantqr.entity.RestaurantOrder;
import com.restaurant.restaurantqr.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins={"http://localhost:5173","http://10.88.19.221:5173"})
public class OrderController {
	private final OrderService orderService;
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	@PostMapping
	public RestaurantOrder placeOrder(@RequestBody OrderRequest request) {
		return orderService.placeOrder(request);
	}
	@GetMapping("/{id}")
	public RestaurantOrder getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
	@GetMapping
	public List<RestaurantOrder> getAllOrders(){
		return orderService.getAllOrders();
	}

}
