package com.aurionpro.services;

import com.aurionpro.model.Order;
import com.aurionpro.model.PaymentType;

public class PaymentService {	
	  public void processPayment(Order order, PaymentType paymentType) {
	        order.setPaymentMode(paymentType);
	        System.out.println("Payment successful. Mode: " + paymentType);
	    }
}
