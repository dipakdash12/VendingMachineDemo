package com.vendingmachine;

public class ProductsSoldOutException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public ProductsSoldOutException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	 
}
