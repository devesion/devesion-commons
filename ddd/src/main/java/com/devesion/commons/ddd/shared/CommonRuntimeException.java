package com.devesion.commons.ddd.shared;

import com.devesion.commons.annotations.patterns.LayerSupertype;

/**
 * Represents exception for which there is no repair code.
 */
@LayerSupertype
public abstract class CommonRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommonRuntimeException() {
	}

	public CommonRuntimeException(String message) {
		super(message);
	}

	public CommonRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonRuntimeException(Throwable cause) {
		super(cause);
	}
}
