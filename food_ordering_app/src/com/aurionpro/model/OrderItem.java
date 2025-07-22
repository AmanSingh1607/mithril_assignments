package com.aurionpro.model;

public class OrderItem {
	private FoodItem foodItem;
	private int quantity;

	public OrderItem(FoodItem foodItem, int quantity) {
		super();
		this.foodItem = foodItem;
		this.quantity = quantity;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

}
