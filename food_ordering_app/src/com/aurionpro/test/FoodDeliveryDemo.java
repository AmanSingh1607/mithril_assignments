package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.exceptions.EmptyDeliveryPartnersListException;
import com.aurionpro.exceptions.EmptyMenuException;
import com.aurionpro.exceptions.EmptyOrderException;
import com.aurionpro.exceptions.FoodItemAlreadyExists;
import com.aurionpro.exceptions.FoodItemNotFoundException;
import com.aurionpro.exceptions.InvalidQuantityException;
import com.aurionpro.model.Admin;
import com.aurionpro.model.Customer;
import com.aurionpro.model.DeliveryPartner;
import com.aurionpro.model.FoodItem;
import com.aurionpro.model.InvoiceGenerator;
import com.aurionpro.model.Order;
import com.aurionpro.model.PaymentType;
import com.aurionpro.model.Serialize;
import com.aurionpro.services.DeliveryService;
import com.aurionpro.services.DiscountService;
import com.aurionpro.services.MenuService;
import com.aurionpro.services.OrderService;
import com.aurionpro.services.PaymentService;

public class FoodDeliveryDemo {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		MenuService menuService = new MenuService();
		DiscountService discountService = new DiscountService();
		DeliveryService deliveryService = new DeliveryService();
		OrderService orderService = new OrderService(discountService, deliveryService);
		PaymentService paymentService = new PaymentService();
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

		while (true) {
			System.out.println("\nWelcome to Mini Food Ordering App");
			System.out.println("1. Admin");
			System.out.println("2. Customer");
			System.out.println("3. Exit");
			String choice = scanner.nextLine().trim();

			switch (choice) {
			case "1":
				Admin admin = new Admin(menuService, discountService, deliveryService);
				runAdminDashboard(admin);
				break;

			case "2":
				Customer customer = new Customer(menuService, orderService);
				runCustomerDashboard(customer, paymentService, invoiceGenerator);
				break;

			case "3":
				System.out.println("Exiting the system!");
				scanner.close();
				return;

			default:
				System.out.println("Please select a valid option.");
			}
		}
	}

	private static void runAdminDashboard(Admin admin) {
		while (true) {
			System.out.println("\nAdmin Dashboard:");
			System.out.println("1. View Menu");
			System.out.println("2. Get Menu Item By ID");
			System.out.println("3. Add Menu Item");
			System.out.println("4. Remove Menu Item");
			System.out.println("5. Change Flat Discount");
			System.out.println("6. Add Delivery Partner");
			System.out.println("7. View Delivery Partners");
			System.out.println("8. Logout");

			String choice = scanner.nextLine().trim();

			try {
				switch (choice) {
				case "1":
					admin.viewMenu();
					break;

				case "2":
					System.out.print("Enter Food Item ID: ");
					int getId = Integer.parseInt(scanner.nextLine());
					FoodItem retrivedItem = admin.getItemById(getId);
					System.out.println(
							retrivedItem.getId() + ": " + retrivedItem.getName() + " - Rs." + retrivedItem.getPrice());
					break;

				case "3":
					System.out.print("Enter Food Item ID: ");
					int id = Integer.parseInt(scanner.nextLine());
					System.out.print("Enter Food Item Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Food Item Price: ");
					double price = Double.parseDouble(scanner.nextLine());

					admin.addMenuItem(id, name, price);
					System.out.println("Item added successfully.");
					break;

				case "4":
					System.out.print("Enter Food Item ID to remove: ");
					int removeId = Integer.parseInt(scanner.nextLine());
					admin.removeMenuItem(removeId);
					System.out.println("Item removed successfully.");
					break;

				case "5":
					System.out.println("Current flat discount is:  Rs." + admin.getFlatDiscount());
					System.out.print("Enter new flat discount amount: ");
					double discount = Double.parseDouble(scanner.nextLine());
					admin.changeFlatDiscount((int) discount);
					System.out.println("Discount updated.");
					break;

				case "6":
					System.out.print("Enter Delivery Partner ID: ");
					int dpId = Integer.parseInt(scanner.nextLine());
					System.out.print("Enter Delivery Partner Name: ");
					String dpName = scanner.nextLine();
					admin.addDeliveryPartner(new DeliveryPartner(dpId, dpName));
					System.out.println("Delivery Partner added.");
					break;

				case "7":
					admin.displayAvailableDeliveryPartners();
					break;

				case "8":
					System.out.println("Logging out from Admin Dashboard...");
					Serialize.serializeMenu(admin.getMenu());
					Serialize.serializeDeliveryPartner(admin.getDeliveryPartner());
					return;

				default:
					System.out.println("Invalid option, please try again.");
				}
			} catch (EmptyMenuException e) {
				System.out.println(e.getMessage());
			} catch (FoodItemNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (FoodItemAlreadyExists e) {
				System.out.println(e.getMessage());
			} catch (EmptyDeliveryPartnersListException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Please enter valid details");
			}
		}
	}

	private static void runCustomerDashboard(Customer customer, PaymentService paymentService,
			InvoiceGenerator invoiceGenerator) {
		while (true) {
			System.out.println("\nCustomer Dashboard:");
			System.out.println("1. View Menu");
			System.out.println("2. Add Item to Cart");
			System.out.println("3. Remove Item from Cart");
			System.out.println("4. Change quantity of item in Cart");
			System.out.println("5. View Cart");
			System.out.println("6. Checkout");
			System.out.println("7. Logout");

			String choice = scanner.nextLine().trim();

			try {
				switch (choice) {
				case "1":
					customer.viewMenu();
					break;

				case "2":
					System.out.print("Enter Food Item ID to add: ");
					int id = Integer.parseInt(scanner.nextLine());
					FoodItem item = customer.getItemById(id);
					System.out.print("Enter Quantity: ");
					int qty = Integer.parseInt(scanner.nextLine());

					customer.addToCart(item, qty);
					break;

				case "3":
					System.out.print("Enter the ID of the item to remove: ");
					int removeId = scanner.nextInt();
					customer.removeItemFromCart(removeId);
					System.out.println("Successfully removed item");
					break;

				case "4":
					System.out.print("Enter the ID of the item to change quantity: ");
					int changeQuantityId = scanner.nextInt();
					System.out.print("Enter the new quantity of the item: ");
					int newQuantity = scanner.nextInt();
					if (newQuantity <= 0) {
						throw new InvalidQuantityException("Please enter a valid quantity");
					}
					customer.changeQuantityOfItemInCart(changeQuantityId, newQuantity);
					System.out.println("Sucessfully changed the quantity");
					break;

				case "5":
					customer.viewCart();
					break;

				case "6":
					Order order = customer.checkout();
					System.out.println("Total amount to pay is : Rs." + (order.getTotalAmount() - order.getDiscount()));
					PaymentType paymentType;
					while (true) {
						System.out.println("Select Payment Mode: \n(1) CASH\n(2) UPI");
						String paymentChoice = scanner.nextLine().trim();

						if (paymentChoice.equals("1")) {
							paymentType = PaymentType.CASH;
							break;
						} else if (paymentChoice.equals("2")) {
							paymentType = PaymentType.UPI;
							break;
						} else {
							System.out.println("Please enter a valid choice");
						}
					}
					paymentService.processPayment(order, paymentType);

					invoiceGenerator.generateInvoice(order);
					break;

				case "7":
					System.out.println("Logging out from Customer Dashboard...");
					return;

				default:
					System.out.println("Invalid option, please try again.");
				}
			} catch (FoodItemNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (InvalidQuantityException e) {
				System.out.println(e.getMessage());
			} catch (EmptyDeliveryPartnersListException e) {
				System.out.println(e.getMessage());
			} catch (EmptyMenuException e) {
				System.out.println(e.getMessage());
			} catch (EmptyOrderException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				 System.out.println("Please enter valid details");
			}
		}
	}
}
