package com.aurionpro.model;

import java.io.Serializable;

public class DeliveryPartner implements Serializable{
	private static final long serialVersionUID = 2L;
	private int deliveryPartnerId;
	private String deliveryPartnerName;

	public DeliveryPartner(int deliveryPartnerId, String deliveryPartnerName) {
		super();
		this.deliveryPartnerId = deliveryPartnerId;
		this.deliveryPartnerName = deliveryPartnerName;
	}

	public int getDeliveryPartnerId() {
		return deliveryPartnerId;
	}

	public String getDeliveryPartnerName() {
		return deliveryPartnerName;
	}

}
