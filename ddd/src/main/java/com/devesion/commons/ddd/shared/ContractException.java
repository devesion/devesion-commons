package com.devesion.commons.ddd.shared;

/**
 * Represents contract exception.
 */
public class ContractException extends CommonRuntimeException {

	private static final long serialVersionUID = 1L;

	public ContractException() {
	}

	public ContractException(String message) {
		super(message);
	}

	public ContractException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContractException(Throwable cause) {
		super(cause);
	}
}
