package com.devesion.commons.utils.crypto;

public class InitializationVector extends RandomEntity {

	private static final int DEFAULT_VECTOR_SIZE = 32;

	private InitializationVector(int length) {
		super(length);
	}

	private InitializationVector(byte[] value) {
		super(value);
	}

	public static InitializationVector fromBytes(byte[] value) {
		return new InitializationVector(value);
	}

	public static InitializationVector randomVector(int length) {
		return new InitializationVector(length);
	}

	public static InitializationVector emptyVector() {
		byte[] value = new byte[DEFAULT_VECTOR_SIZE];
		return new InitializationVector(value);
	}
}
