package com.restaurant.restaurantqr.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.Bill;
import com.restaurant.restaurantqr.entity.Payment;
import com.restaurant.restaurantqr.repository.BillRepository;
import com.restaurant.restaurantqr.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	private final PaymentRepository paymentRepository;
	private final BillRepository billRepository;
	public PaymentService(PaymentRepository paymentRepository,BillRepository billRepository) {
		this.paymentRepository=paymentRepository;
		this.billRepository=billRepository;
	}
	@Transactional
	public Payment makePayment(Long billId,String paymentMethod) {
		Bill bill=billRepository.findById(billId).orElseThrow(()->new RuntimeException("bill not Found"));
		if(!"PENDING".equalsIgnoreCase(bill.getPaymentStatus())) {
			throw new RuntimeException("Bill is Already Paid");

		}
		Set<String> methods=Set.of("CASH","CARD","UPI");
		String method=paymentMethod.toUpperCase();
		if(!methods.contains(method)) {
			throw new RuntimeException("Invalid payment method");
		}
		Payment payment=new Payment();
		payment.setBill(bill);
		payment.setAmount(bill.getTotalAmount());
		payment.setPaymentMethod(method);
		payment.setPaymentStatus("SUCCESS");
		payment.setPaymentTime(LocalDateTime.now());
		bill.setPaymentStatus("SUCCESS");
		billRepository.save(bill);
		return paymentRepository.save(payment);
	}


}
