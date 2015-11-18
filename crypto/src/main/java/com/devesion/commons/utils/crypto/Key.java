package com.devesion.commons.utils.crypto;

public class Key extends RandomEntity {

	public Key(int length) {
		super(length);
	}

	public Key(byte[] value) {
		super(value);
	}

	public static Key randomKey() {
		byte[] value = SecureRandomGenerator.generateRandomBytes(16);
		return new Key(value);
	}
}
