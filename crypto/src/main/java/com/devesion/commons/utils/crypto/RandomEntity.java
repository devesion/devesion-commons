package com.devesion.commons.utils.crypto;

import com.google.common.base.Preconditions;
import lombok.Getter;

class RandomEntity {

	@Getter
	private final byte[] value;

	public RandomEntity(byte[] value) {
		this.value = value;
	}

	public RandomEntity(int length) {
		this.value = SecureRandomGenerator.generateRandomBytes(length);
	}

	public byte getByte(int number) {
		Preconditions.checkArgument(number >= 0);
		Preconditions.checkArgument(number < value.length);
		return value[number];
	}

	public int getLength() {
		return value.length;
	}
}
