package com.restaurant.restaurantqr.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.restaurant.restaurantqr.dto.OrderRequest;
import com.restaurant.restaurantqr.entity.FoodItem;
import com.restaurant.restaurantqr.entity.OrderItem;
import com.restaurant.restaurantqr.entity.RestaurantOrder;
import com.restaurant.restaurantqr.entity.TableSession;
import com.restaurant.restaurantqr.repository.FoodItemRepository;
import com.restaurant.restaurantqr.repository.OrderItemRepository;
import com.restaurant.restaurantqr.repository.RestaurantOrderRepository;
import com.restaurant.restaurantqr.repository.TableSessionRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	private final RestaurantOrderRepository orderRepository;
	private final OrderItemRepository itemRepository;
	private final FoodItemRepository foodRepository;
	private final TableSessionRepository sessionRepository;
	public OrderService(RestaurantOrderRepository orderRepository,
			OrderItemRepository itemRepository,
			FoodItemRepository foodRepository,
			TableSessionRepository sessionRepository ) {
		this.orderRepository=orderRepository;
		this.foodRepository=foodRepository;
		this.itemRepository=itemRepository;
		this.sessionRepository=sessionRepository;
	}
	@Transactional
	public RestaurantOrder placeOrder(OrderRequest request) {
		TableSession session=sessionRepository.findById(request.getSessionId()).orElseThrow(()->new RuntimeException("Session Not Found"));
		if(!"ACTIVE".equalsIgnoreCase(session.getStatus())) {
			throw new RuntimeException("Table session is not active");
		}
		if(request.getItems()==null||request.getItems().isEmpty()) {
			throw new RuntimeException("Order Must Contain food Items");
		}
		RestaurantOrder order=new RestaurantOrder();
		order.setOrderTime(LocalDateTime.now());
		order.setOrderStatus("PENDING");
		order.setTableSession(session);
		order.setTotalAmount(BigDecimal.ZERO);
		order=orderRepository.save(order);
		BigDecimal total=BigDecimal.ZERO;
		List<OrderItem> orderItems=new ArrayList<>();
		for(OrderRequest.ItemRequest itemRequest:request.getItems()) {
			if(itemRequest.getQuantity()==null ||itemRequest.getQuantity()<=0) {
				throw new RuntimeException("Quantity must be greater than zero");
			}
			FoodItem food=foodRepository.findById(itemRequest.getFoodId()).orElseThrow(()->new RuntimeException("Food Item Not Found"));

			if(!food.isAvailability()) {
				throw new RuntimeException(food.getFoodName()+"is not available");
			}
			BigDecimal price=BigDecimal.valueOf(food.getPrice());
			OrderItem orderItem =new OrderItem();
			orderItem.setRestaurantOrder(order);
			orderItem.setFoodItem(food);
			orderItem.setQuantity(itemRequest.getQuantity());
			orderItem.setPrice(price);
			orderItems.add(orderItem);
			BigDecimal itemTotal=price.multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            total=total.add(itemTotal);


		}
		itemRepository.saveAll(orderItems);
		order.setTotalAmount(total);
		return orderRepository.save(order);

	}
	public RestaurantOrder getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(()->new RuntimeException("OrderNot Found"));
	}
	public List<RestaurantOrder> getAllOrders(){
		return orderRepository.findAllWithItems();
	}

}
