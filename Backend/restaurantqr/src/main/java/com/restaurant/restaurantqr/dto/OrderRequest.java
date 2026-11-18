package com.restaurant.restaurantqr.dto;

import java.util.List;

public class OrderRequest {
	private  Long sessionId;
	private List<ItemRequest> items;
	public OrderRequest() {

	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId=sessionId;
	}
	public List<ItemRequest> getItems(){
		return items;
	}
	public void setItems(List<ItemRequest> items) {
		this.items=items;
	}
	public static class ItemRequest{
		private Long foodId;
		private Integer quantity;
		public ItemRequest() {

		}
		public Long getFoodId() {
			return foodId;
		}
		public void setFoodId(Long foodId) {
			this.foodId=foodId;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity=quantity;
		}

	}

}
