package com.aurionpro.exceptions;

public class FoodItemAlreadyExists extends Exception {

	private static final long serialVersionUID = 2L;
	
	public FoodItemAlreadyExists(String message) {
		super(message);
	}

}
