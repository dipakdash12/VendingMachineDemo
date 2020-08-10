package com.vendingmachine;

public class NotFullPaidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;
    private long remaining;
    
	public NotFullPaidException(String message, long remaining) {
		super();
		this.message = message;
		this.remaining = remaining;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getRemaining() {
		return remaining;
	}

	public void setRemaining(long remaining) {
		this.remaining = remaining;
	}
    

}
