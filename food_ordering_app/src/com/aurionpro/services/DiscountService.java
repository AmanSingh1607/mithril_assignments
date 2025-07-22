package com.aurionpro.services;

public class DiscountService {
	private static final double THRESHOLD = 500;
	private double flatDiscount = 100;
	
	public double applyDiscount(double totalAmount) {
		if(totalAmount >  THRESHOLD) {
			return flatDiscount;
		}
		return 0;
	}
	
	public double getFlatDiscount() {
		return flatDiscount;
	}
	public void setFlatDiscount(double flatDiscount) {
		this.flatDiscount = flatDiscount;
	}
}
