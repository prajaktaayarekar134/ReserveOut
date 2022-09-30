package com.stackroute.paymentservice.exception;

public class BillNotFoundException extends RuntimeException {
	private final String errorMessage;
	private static final long serialVersionUID = 1L;

	public BillNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
