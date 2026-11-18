package com.restaurant.restaurantqr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.entity.Bill;
import com.restaurant.restaurantqr.entity.RestaurantOrder;
import com.restaurant.restaurantqr.entity.TableSession;
import com.restaurant.restaurantqr.repository.BillRepository;
import com.restaurant.restaurantqr.repository.RestaurantOrderRepository;
import com.restaurant.restaurantqr.repository.TableSessionRepository;

import jakarta.transaction.Transactional;

@Service
public class BillService {
	private final BillRepository billRepository;
	private final RestaurantOrderRepository orderRepository;
	private final TableSessionRepository sessionRepository;
	public BillService(BillRepository billRepository,RestaurantOrderRepository orderRepository,TableSessionRepository sessionRepository) {
		this.billRepository=billRepository;
		this.orderRepository=orderRepository;
		this.sessionRepository=sessionRepository;
	}
	@Transactional
	public Bill generateBill(Long sessionId) {
		TableSession session=sessionRepository.findById(sessionId).orElseThrow(()->new RuntimeException("Session Not Found"));
		if(!"CLOSED".equalsIgnoreCase(session.getStatus())) {
			throw new RuntimeException("Close the table session before generetaing the final bill");

		}
		if(billRepository.findByTableSession_SessionId(sessionId).isPresent()) {
			throw new RuntimeException("Bill Already generated");
		}
		List<RestaurantOrder> orders=orderRepository.findByTableSession_SessionId(sessionId);
		if(orders.isEmpty()) {
			throw new RuntimeException("No orders found for this session:");
		}
		BigDecimal subtotal=BigDecimal.ZERO;
		for(RestaurantOrder order:orders) {
			if(!"COMPLETED".equalsIgnoreCase(order.getOrderStatus())) {
				throw new RuntimeException("All orders must be completd before billing");
			}
			subtotal=subtotal.add(order.getTotalAmount());

		}
		BigDecimal tax=subtotal.multiply(new BigDecimal("0.05"));
		BigDecimal total=subtotal.add(tax);
		Bill bill=new Bill();
		bill.setTableSession(session);
		bill.setSubtotal(subtotal);
		bill.setTax(tax);
		bill.setTotalAmount(total);
		bill.setBillTime(LocalDateTime.now());
		bill.setPaymentStatus("PENDING");
		return billRepository.save(bill);

	}
	public Bill getBill(Long billId) {
		return billRepository.findById(billId).orElseThrow(()->new RuntimeException("Bill not FOund"));
	}

}
