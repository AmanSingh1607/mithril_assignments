package com.aurionpro.model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serialize {
	public static void serializeMenu(List<FoodItem> menu) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null; 
		try {
			fos = new FileOutputStream("menu.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(menu);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fos.close();
				oos.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void serializeDeliveryPartner(List<DeliveryPartner> deliveryPartner) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null; 
		try {
			fos = new FileOutputStream("delivery_partner.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(deliveryPartner);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fos.close();
				oos.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
