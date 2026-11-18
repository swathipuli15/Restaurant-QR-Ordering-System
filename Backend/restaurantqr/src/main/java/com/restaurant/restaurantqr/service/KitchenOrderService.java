package com.restaurant.restaurantqr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.RestaurantOrder;
import com.restaurant.restaurantqr.repository.RestaurantOrderRepository;

@Service
public class KitchenOrderService  {
	private final RestaurantOrderRepository orderRepository;
	public KitchenOrderService(RestaurantOrderRepository orderRepository) {
		this.orderRepository=orderRepository;
	}
	public List<RestaurantOrder> getAllOrders(){
		return orderRepository.findAllByOrderByOrderTimeDesc();
	}
	public RestaurantOrder updateOrderStatus(Long id,String status) {
		RestaurantOrder order=orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found"));
		if(!status.equals("PREPARING")&&!status.equals("READY")&& !status.equals("COMPLETED")) {
			throw new RuntimeException("Invalid Order Status");
		}
		order.setOrderStatus(status);
		return orderRepository.save(order);
	}

}
