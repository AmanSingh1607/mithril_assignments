package com.aurionpro.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserialize {
	@SuppressWarnings("unchecked")
	public static List<FoodItem> deserializeMenu() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("menu.txt");
			ois = new ObjectInputStream(fis);
			
			List<FoodItem> menu = (List<FoodItem>) ois.readObject();
			return menu;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				fis.close();
				ois.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<DeliveryPartner> deserializeDeliveryPartner() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("delivery_partner.txt");
			ois = new ObjectInputStream(fis);
			
			List<DeliveryPartner> menu = (List<DeliveryPartner>) ois.readObject();
			return menu;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				fis.close();
				ois.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
