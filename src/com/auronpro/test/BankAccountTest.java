package com.auronpro.test;

import com.auronpro.model.BankAccount;
import com.auronpro.model.InsufficientFundsException;
import com.auronpro.model.NegativeAmountException;

public class BankAccountTest {
	public static void main(String[] args) {
		BankAccount acc1 = new BankAccount(1, "Aman", 10000);
		makeDeposit(acc1, 5000);
		System.out.println(acc1);
		makeDeposit(acc1, -10000);
		System.out.println(acc1);
		makeWithdraw(acc1, 5000);
		System.out.println(acc1);
		makeWithdraw(acc1, 40000);
		System.out.println(acc1);
		makeWithdraw(acc1, -10000);
		System.out.println(acc1);
	}
	
	private static void makeDeposit(BankAccount acc, double amount) {
		try {
			acc.deposit(amount);
		} catch (NegativeAmountException e) {
			e.printStackTrace();
		}
	}
	
	private static void makeWithdraw(BankAccount acc, double amount) {
		try {
			acc.withdraw(amount);
		} catch (InsufficientFundsException e) {
			e.printStackTrace();
		}
		catch(NegativeAmountException e) {
			e.printStackTrace();
		}
	}
}
