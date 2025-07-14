package com.auronpro.model;

public class BankAccount {
	private int id;
	private String name;
	private double balance;

	public BankAccount(int id, String name, double balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
	
	public void withdraw(double amount) throws InsufficientFundsException, NegativeAmountException {
		if(amount <= 0) {
			throw new NegativeAmountException("Please enter an amount that is greater than zero");
		}
		else if(balance - amount > 0) {
			balance -= amount;
			System.out.println("Rs. " +amount +" withdrawed successfully");
		} 
		else {
			throw new InsufficientFundsException("Transaction Failed! Not sufficient balance in the account");
		}
	}

	
	public void deposit(double amount) throws NegativeAmountException {
		if(amount > 0) {
			balance += amount;
			System.out.println("Rs. " +amount +" deposited successfully");
		}
		else {
			throw new NegativeAmountException("Please enter an amount that is greater than zero");
		}
	}
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

}
