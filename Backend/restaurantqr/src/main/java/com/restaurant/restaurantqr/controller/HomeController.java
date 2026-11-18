package com.restaurant.restaurantqr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins={"http://10.88.19.221:5173", "http://localhost:5173"})
public class HomeController {
	@GetMapping("/api/home")
	public String home() {
		return "Welcome to QR Code Based Restaurant Management System";
	}

}
