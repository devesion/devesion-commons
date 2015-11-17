package com.devesion.commons.utils.crypto;

import com.devesion.commons.ddd.shared.InternalException;

public class CryptoException extends InternalException {

	public CryptoException(String message) {
		super(message);
	}

	public CryptoException(String message, Throwable cause) {
		super(message, cause);
	}
}
