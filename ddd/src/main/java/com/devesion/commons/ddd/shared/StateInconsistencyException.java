package com.devesion.commons.ddd.shared;

/**
 * Represents the loss of logical consistency.
 */
public class StateInconsistencyException extends InternalException {

	private Object state;

	public StateInconsistencyException(Object state) {
		this.state = state;
	}

	public StateInconsistencyException(Object state, String message) {
		super(message);
		this.state = state;
	}

	public StateInconsistencyException(Object state, String message, Throwable cause) {
		super(message, cause);
		this.state = state;
	}

	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("state inconsistency found for object '");
		sb.append(state);
		sb.append("'");
		return sb.toString();
	}
}
