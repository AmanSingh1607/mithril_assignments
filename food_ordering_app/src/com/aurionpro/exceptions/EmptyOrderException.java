package com.aurionpro.exceptions;

public class EmptyOrderException extends Exception {
	
	private static final long serialVersionUID = 6L;

	public EmptyOrderException(String message) {
		System.out.println(message);
	}
}
