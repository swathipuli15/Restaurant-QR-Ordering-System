package com.restaurant.restaurantqr.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.service.DashboardService;

@RestController
@CrossOrigin(origins={"http://localhost:5173","http://localhost:5174", "http://10.88.19.221:5173"})
@RequestMapping("/api/dashboard")
public class DashboardController {
	private final DashboardService dashboardService;
	public DashboardController(DashboardService dashboardService) {
		this.dashboardService=dashboardService;
	}
	@GetMapping
	public Map<String,Object> getDashboardData(){
		return dashboardService.getDashboardData();
	}


}
