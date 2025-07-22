package com.aurionpro.model;

public class InvoiceGenerator {
	public void generateInvoice(Order order) {
        System.out.println("\n\t\tINVOICE");
        System.out.println("-------------------------------");

        for (OrderItem item : order.getOrderItems()) {
            double itemTotal = item.getQuantity() * item.getFoodItem().getPrice();
            System.out.printf("%-20s x %d = ₹%.2f%n",
                item.getFoodItem().getName(),
                item.getQuantity(),
                itemTotal);
        }

        System.out.println("-------------------------------");
        System.out.printf("Subtotal: ₹%.2f%n", order.getTotalAmount());
        System.out.printf("Discount: -₹%.2f%n", order.getDiscount());
        System.out.printf("Final Total: ₹%.2f%n", order.getFinalAmount());
        System.out.printf("Payment Mode: %s%n", order.getPaymentType());
        System.out.printf("Delivery Partner: %s%n", order.getDeliveryPartner().getDeliveryPartnerName());
        System.out.println("-------------------------------");
        System.out.println("Thank you for ordering!");
    }
}
