package com.aurionpro.model;

import java.util.List;

public class Order {
	private List<OrderItem> orderItems;
	private double totalAmount;
	private double discount;
	private double finalAmount;
	private PaymentType paymentType;
	private DeliveryPartner deliveryPartner;
	
	public Order(List<OrderItem> orderItems, double totalAmount, double discount, double finalAmount, PaymentType paymentType,
			DeliveryPartner deliveryPartner) {
		super();
		this.orderItems = orderItems;
		this.totalAmount = totalAmount;
		this.discount = discount;
		this.finalAmount = finalAmount;
		this.paymentType = paymentType;
		this.deliveryPartner = deliveryPartner;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setPaymentMode(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
	
}
