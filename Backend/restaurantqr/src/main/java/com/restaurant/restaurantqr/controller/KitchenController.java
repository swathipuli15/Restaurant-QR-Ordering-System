package com.restaurant.restaurantqr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.entity.RestaurantOrder;
import com.restaurant.restaurantqr.repository.RestaurantOrderRepository;

@RestController
@RequestMapping("/api/kitchen")
@CrossOrigin(origins={"http://localhost:5173", "http://10.88.19.221:5173"})
public class KitchenController {
	private final RestaurantOrderRepository orderRepository;
	public KitchenController(RestaurantOrderRepository orderRepository) {
		this.orderRepository=orderRepository;
	}
	@GetMapping("/orders")
	public List<RestaurantOrder> getKitchenOrders(){
		return orderRepository.findAllWithItems();
	}
	@PutMapping("/orders/{id}/status")
	public RestaurantOrder updateOrderStatus(@PathVariable Long id,@RequestParam String status) {
		RestaurantOrder order=orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not Found"));
		if(!status.equalsIgnoreCase("PENDING")&&!status.equalsIgnoreCase("PREPARING")
				&&!status.equalsIgnoreCase("COMPLETED")) {
			throw new RuntimeException("Invalid Order Status");
		}
		order.setOrderStatus(status.toUpperCase());
		RestaurantOrder updatedOrder=orderRepository.save(order);
		System.out.println("Update Order Id:" +updatedOrder.getOrderId());
		System.out.println("Updated Order Status:" +updatedOrder.getOrderStatus());
		return updatedOrder;
//		return orderRepository.save(order);
		
	}

}
