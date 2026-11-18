package com.restaurant.restaurantqr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurantqr.entity.Payment;
import com.restaurant.restaurantqr.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins={"http://localhost=5173","http://10.88.19.221:5173"})
public class PaymentController {
	private final PaymentService paymentService;
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	@PostMapping("/{billId}")
	public Payment makePayment(@PathVariable Long billId,@RequestParam String paymentMethod) {
		return paymentService.makePayment(billId,paymentMethod);
	}

}
