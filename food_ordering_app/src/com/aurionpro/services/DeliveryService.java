package com.aurionpro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aurionpro.exceptions.EmptyDeliveryPartnersListException;
import com.aurionpro.model.DeliveryPartner;
import com.aurionpro.model.Deserialize;

public class DeliveryService {
	private List<DeliveryPartner> deliveryPartners = new ArrayList<>();

	public DeliveryService() {
		super();
		this.deliveryPartners = Deserialize.deserializeDeliveryPartner();
	}
	
	public void addDeliveryPartner(DeliveryPartner deliveryPartner) {
		deliveryPartners.add(deliveryPartner);
	}
	
	public List<DeliveryPartner> getDeliveryPartners() {
		return deliveryPartners;
	}
	
	public DeliveryPartner assignDeliveryPartner() throws EmptyDeliveryPartnersListException {
		if(deliveryPartners.isEmpty()) {
			throw new EmptyDeliveryPartnersListException("No Available Delivery Partners");
		}
		Random rand = new Random();
		int size = deliveryPartners.size();
		return deliveryPartners.get(rand.nextInt(0,size));
	}
}
