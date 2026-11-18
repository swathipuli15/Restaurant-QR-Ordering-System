package com.restaurant.restaurantqr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.entity.Bill;
import com.restaurant.restaurantqr.service.BillService;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins={"http://localhost:5173", "http://10.88.19.221:5173"})
public class BillController {
	private final BillService billService;
	public BillController(BillService billService) {
		this.billService=billService;
	}
	@PostMapping("/session/{sessionId}")
	public Bill generateBill(@PathVariable Long sessionId) {
		return billService.generateBill(sessionId);
	}
	@GetMapping("/{billId}")
	public Bill getBill(@PathVariable Long billId) {
		return billService.getBill(billId);

	}

}
