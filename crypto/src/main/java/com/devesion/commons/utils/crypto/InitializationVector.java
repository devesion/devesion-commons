package com.devesion.commons.utils.crypto;

public class InitializationVector extends RandomEntity {

	public InitializationVector(int length) {
		super(length);
	}

	public InitializationVector(byte[] value) {
		super(value);
	}
}
