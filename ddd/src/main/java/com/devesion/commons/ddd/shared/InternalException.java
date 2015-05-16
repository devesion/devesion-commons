package com.devesion.commons.ddd.shared;

/**
 * Represents internal system exception.
 */
public class InternalException extends CommonRuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalException() {
	}

	public InternalException(String message) {
		super(message);
	}

	public InternalException(String message, Throwable cause) {
		super(message, cause);
	}
}
