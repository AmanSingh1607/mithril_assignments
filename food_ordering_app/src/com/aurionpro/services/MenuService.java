package com.aurionpro.services;

import java.util.ArrayList;
import java.util.List;

import com.aurionpro.exceptions.FoodItemAlreadyExists;
import com.aurionpro.exceptions.FoodItemNotFoundException;
import com.aurionpro.model.Deserialize;
import com.aurionpro.model.FoodItem;

public class MenuService {
	private List<FoodItem> menu = new ArrayList<FoodItem>();

	public MenuService() {
		super();
		this.menu = Deserialize.deserializeMenu();
	}

	public List<FoodItem> getMenu() {
		return menu;
	}
	
	public void addItem(FoodItem item) throws FoodItemAlreadyExists {
		for(FoodItem f:menu) {
			if(f.getId() == item.getId()) {
				throw new FoodItemAlreadyExists("Food item with the specified id already exists");
			} 
		}
		menu.add(item);
	}
	
	public FoodItem getItemByID(int id) throws FoodItemNotFoundException {
		for(FoodItem f:menu) {
			if(f.getId() == id) {
				return f;
			} 
		}
		throw new FoodItemNotFoundException("Food item with the specified id is not found");
	}
	
	public void removeItem(int id) throws FoodItemNotFoundException {
		for(FoodItem f:menu) {
			if(f.getId() == id) {
				menu.remove(f);
				return;
			} 
		}
		throw new FoodItemNotFoundException("Food item with the specified id is not found");
	}
}
