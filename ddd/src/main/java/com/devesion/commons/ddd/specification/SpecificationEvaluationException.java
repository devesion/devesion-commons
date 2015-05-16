package com.devesion.commons.ddd.specification;

public class SpecificationEvaluationException extends RuntimeException {
	public SpecificationEvaluationException() {
	}

	public SpecificationEvaluationException(Throwable cause) {
		super(cause);
	}

	public SpecificationEvaluationException(String message) {
		super(message);
	}

	public SpecificationEvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpecificationEvaluationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
