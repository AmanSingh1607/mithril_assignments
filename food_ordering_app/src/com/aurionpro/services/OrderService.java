package com.aurionpro.services;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.exceptions.EmptyDeliveryPartnersListException;
import com.aurionpro.exceptions.EmptyOrderException;
import com.aurionpro.exceptions.FoodItemNotFoundException;
import com.aurionpro.model.DeliveryPartner;
import com.aurionpro.model.FoodItem;
import com.aurionpro.model.Order;
import com.aurionpro.model.OrderItem;

public class OrderService {
	private List<OrderItem> currentOrderItems = new ArrayList<>();
    private DiscountService discountService;
    private DeliveryService deliveryService;

    public OrderService(DiscountService discountService, DeliveryService deliveryService) {
        this.discountService = discountService;
        this.deliveryService = deliveryService;
    }

    public void addOrderItem(FoodItem item, int quantity) {
    	for(OrderItem oi:currentOrderItems) {
    		if(oi.getFoodItem().getId() == item.getId()) {
    			oi.setQuantity(oi.getQuantity() + quantity);
    			return;
    		}
    	}
        currentOrderItems.add(new OrderItem(item, quantity));
    }
    
    public void removeOrderItem(int id) throws EmptyOrderException, FoodItemNotFoundException {
    	if(currentOrderItems.isEmpty()) {
    		throw new EmptyOrderException("Order is Empty... First add something");
    	}
    	for(OrderItem oi : currentOrderItems) {
    		if(oi.getFoodItem().getId() == id) {
    			currentOrderItems.remove(oi);
    			return;
    		}
    	}
    	throw new FoodItemNotFoundException("Food item with the specified ID is not found");
    }
    
    
    public void changeQuantityOfOrderItem(int id,int quantity) throws EmptyOrderException, FoodItemNotFoundException {
    	if(currentOrderItems.isEmpty()) {
    		throw new EmptyOrderException("Order is Empty... First add something");
    	}
    	for(OrderItem oi : currentOrderItems) {
    		if(oi.getFoodItem().getId() == id) {
    			oi.setQuantity(quantity);
    			return;
    		}
    	}
    	throw new FoodItemNotFoundException("Food item with the specified ID is not found");
    }

    public void printOrderSummary() throws EmptyOrderException {
    	if(currentOrderItems.isEmpty()) {
    		throw new EmptyOrderException("Order is Empty... First add something");
    	}
        for (OrderItem oi : currentOrderItems) {
            System.out.println(oi.getFoodItem().getName() + " x " + oi.getQuantity() +
                               " = Rs." + oi.getFoodItem().getPrice() * oi.getQuantity());
        }
    }

    public Order createOrder() throws EmptyDeliveryPartnersListException, EmptyOrderException {
        double totalAmount = calculateTotal();
        double discount = discountService.applyDiscount(totalAmount);
        double finalAmount = totalAmount - discount;
        DeliveryPartner partner = deliveryService.assignDeliveryPartner();
        currentOrderItems.clear();
        return new Order(currentOrderItems, totalAmount, discount, finalAmount, null, partner);
    }

    private double calculateTotal() throws EmptyOrderException {
        double total = 0.0;
        if(currentOrderItems.isEmpty()) {
        	throw new EmptyOrderException("Order is Empty... First add something");
        }
        for (OrderItem item : currentOrderItems) {
            total += item.getFoodItem().getPrice() * item.getQuantity();
        }
        return total;
    }
}
