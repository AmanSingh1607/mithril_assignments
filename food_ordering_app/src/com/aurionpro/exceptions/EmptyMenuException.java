package com.aurionpro.exceptions;

public class EmptyMenuException extends Exception {
	
	private static final long serialVersionUID = 4L;

	public EmptyMenuException(String message) {
		super(message);
	}
}
