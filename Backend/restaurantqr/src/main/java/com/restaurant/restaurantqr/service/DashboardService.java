package com.restaurant.restaurantqr.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.repository.FoodItemRepository;
import com.restaurant.restaurantqr.repository.PaymentRepository;
import com.restaurant.restaurantqr.repository.RestaurantOrderRepository;
import com.restaurant.restaurantqr.repository.RestaurantTableRepository;

@Service
public class DashboardService {
	private final RestaurantTableRepository tableRepository;
	private final FoodItemRepository foodRepository;
	private final RestaurantOrderRepository orderRepository;
	private final PaymentRepository paymentRepository;
 public DashboardService(RestaurantTableRepository tableRepository,FoodItemRepository foodRepository,RestaurantOrderRepository orderRepository,PaymentRepository paymentRepository) {
	 this.tableRepository=tableRepository;
	 this.foodRepository=foodRepository;
	 this.orderRepository=orderRepository;
	 this.paymentRepository=paymentRepository;
 }
 public Map<String,Object> getDashboardData(){
	 Map<String,Object> dashboard=new HashMap<>();
	 dashboard.put("totalTables",tableRepository.count());
	 dashboard.put("availableTables",tableRepository.countByStatus("AVAILABLE"));
	 dashboard.put("occupiedTables",tableRepository.countByStatus("OCCUPIED"));
	 dashboard.put("totalFoodItems",foodRepository.count());
	 dashboard.put("totalOrders",orderRepository.count());
	 dashboard.put("pendingOrders",orderRepository.countByOrderStatus("PENDING"));
	 BigDecimal totalRevenue=paymentRepository.findAll().stream().filter(payment->"SUCCESS".equalsIgnoreCase(payment.getPaymentStatus())).map(payment->payment.getAmount()).reduce(BigDecimal.ZERO,BigDecimal::add);
	 dashboard.put("totalRevenue", totalRevenue);
	 return dashboard;
 }
}
