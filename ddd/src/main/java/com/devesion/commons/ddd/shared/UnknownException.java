package com.devesion.commons.ddd.shared;

/**
 * Represents unknown exception - not internal and not contract.
 */
public class UnknownException extends CommonRuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownException() {
	}

	public UnknownException(String message) {
		super(message);
	}

	public UnknownException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownException(Throwable cause) {
		super(cause);
	}
}
