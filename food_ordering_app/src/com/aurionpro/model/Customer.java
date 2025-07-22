package com.aurionpro.model;

import com.aurionpro.exceptions.EmptyDeliveryPartnersListException;
import com.aurionpro.exceptions.EmptyMenuException;
import com.aurionpro.exceptions.EmptyOrderException;
import com.aurionpro.exceptions.FoodItemNotFoundException;
import com.aurionpro.exceptions.InvalidQuantityException;
import com.aurionpro.services.MenuService;
import com.aurionpro.services.OrderService;

public class Customer {
	private MenuService menuService;
	private OrderService orderService;
	
	public Customer(MenuService menuService,OrderService orderService) {
		this.menuService = menuService;
		this.orderService = orderService;
	}

	public void viewMenu() throws EmptyMenuException {
		if(menuService.getMenu().isEmpty()) {
			throw new EmptyMenuException("Menu is empty. Nothing to display");
		}
        System.out.println("Menu:");
        for (FoodItem item : menuService.getMenu()) {
            System.out.println(item.getId() + ": " + item.getName() + " - Rs." + item.getPrice());
        }
    }

    // Add item to cart
    public void addToCart(FoodItem item, int quantity) throws InvalidQuantityException {
	   if(quantity <= 0) {
		   throw new InvalidQuantityException("Please enter a valid quantity");
       } 
        orderService.addOrderItem(item, quantity);
        System.out.println("Added " + quantity + " x " + item.getName() + " to cart.");
    }

    public FoodItem getItemById(int id) throws FoodItemNotFoundException {
    	return menuService.getItemByID(id);
    }
    
    public void removeItemFromCart(int id) throws EmptyOrderException, FoodItemNotFoundException {
    	orderService.removeOrderItem(id);
    }
    
    public void changeQuantityOfItemInCart(int id,int quantity) throws EmptyOrderException, FoodItemNotFoundException {
    	orderService.changeQuantityOfOrderItem(id, quantity);
    }
    
    public void viewCart()  {
        try {
			orderService.printOrderSummary();
		} catch (EmptyOrderException e) {
			System.out.println(e.getMessage());
		}
    }

    public Order checkout() throws EmptyDeliveryPartnersListException, EmptyOrderException {
        System.out.println("Finalizing your order...");
        return orderService.createOrder();
    }
}
