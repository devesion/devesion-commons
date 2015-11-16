package com.devesion.commons.utils.crypto;

import org.bouncycastle.util.encoders.Base64;

public final class Message {

	private final byte[] value;

	private Message(byte[] value) {
		this.value = value;
	}

	public static Message fromBytes(byte[] value) {
		return new Message(value);
	}

	public static Message fromBase64(String base64Value) {
		byte[] value = Base64.decode(base64Value);
		return new Message(value);
	}

	public static Message fromString(String stringValue) {
		byte[] value = stringValue.getBytes();
		return new Message(value);
	}

	public byte[] toBytes() {
		return value;
	}

	public String toBase64() {
		return Base64.toBase64String(value);
	}

	public String toString() {
		return new String(value);
	}
}
