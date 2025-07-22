package com.aurionpro.model;

import java.util.List;

import com.aurionpro.exceptions.EmptyDeliveryPartnersListException;
import com.aurionpro.exceptions.EmptyMenuException;
import com.aurionpro.exceptions.FoodItemAlreadyExists;
import com.aurionpro.exceptions.FoodItemNotFoundException;
import com.aurionpro.services.DeliveryService;
import com.aurionpro.services.DiscountService;
import com.aurionpro.services.MenuService;

public class Admin {
	private MenuService menuService;
	private DiscountService discountService;
	private DeliveryService deliveryService;

	public Admin(MenuService menuService, DiscountService discountService, DeliveryService deliveryService) {
		this.menuService = menuService;
		this.discountService = discountService;
		this.deliveryService = deliveryService;
	}

	public List<FoodItem> getMenu() {
		return menuService.getMenu();
	}

	public List<DeliveryPartner> getDeliveryPartner() {
		return deliveryService.getDeliveryPartners();
	}

	public void displayAvailableDeliveryPartners() throws EmptyDeliveryPartnersListException {
		List<DeliveryPartner> deliveryPartners = deliveryService.getDeliveryPartners();
		if (deliveryPartners.isEmpty()) {
			throw new EmptyDeliveryPartnersListException("No available delivery partners");
		}
		System.out.println("Delivery Partners Details---------------");
		for (DeliveryPartner partner : deliveryPartners) {
			System.out.println("ID : " + partner.getDeliveryPartnerId() + " Name: " + partner.getDeliveryPartnerName());
		}
	}

	public void addMenuItem(int id, String name, double price) throws FoodItemAlreadyExists {
		FoodItem foodItem = new FoodItem(id, name, price);
		menuService.addItem(foodItem);
	}

	public void removeMenuItem(int id) throws FoodItemNotFoundException {
		menuService.removeItem(id);
	}

	public void viewMenu() throws EmptyMenuException {
		if (menuService.getMenu().isEmpty()) {
			throw new EmptyMenuException("Menu is empty. Nothing to display");
		}
		for (FoodItem f : menuService.getMenu()) {
			System.out.println(f.getId() + ": " + f.getName() + " - Rs." + f.getPrice());
		}
	}

	public FoodItem getItemById(int id) throws FoodItemNotFoundException {
		return menuService.getItemByID(id);
	}

	public double getFlatDiscount() {
		return discountService.getFlatDiscount();
	}

	public void changeFlatDiscount(int flatDiscount) {
		discountService.setFlatDiscount(flatDiscount);
	}

	public void addDeliveryPartner(DeliveryPartner deliveryPartner) {
		deliveryService.addDeliveryPartner(deliveryPartner);
	}
}
