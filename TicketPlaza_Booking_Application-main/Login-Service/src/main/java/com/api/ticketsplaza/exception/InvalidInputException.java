package com.api.ticketsplaza.exception;

public class InvalidInputException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorCode;

    public InvalidInputException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidInputException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

	
}
