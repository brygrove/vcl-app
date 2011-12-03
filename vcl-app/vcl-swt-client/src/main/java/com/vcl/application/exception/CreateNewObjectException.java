package com.vcl.application.exception;

public class CreateNewObjectException extends RuntimeException {


	private static final long serialVersionUID = 7345916262954544966L;
	
	public CreateNewObjectException(Class<?> objectType, Exception ex) {
		super("Failed to create a new instance of type " + objectType.getName() + ", Error: " + ex.getMessage(), ex);
	}

}
