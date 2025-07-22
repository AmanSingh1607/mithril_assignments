package com.aurionpro.model;

import java.io.Serializable;

public class FoodItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double price;

	public FoodItem(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

}
