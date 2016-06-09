package com.devesion.commons.utils.crypto;

import com.google.common.io.BaseEncoding;

public final class Message {

	private final byte[] value;

	private Message(byte[] value) {
		this.value = value;
	}

	public static Message fromBytes(byte[] value) {
		return new Message(value);
	}

	public static Message fromBase64(String base64Value) {
		byte[] value = BaseEncoding.base64().decode(base64Value);
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
		return BaseEncoding.base64().encode(value);
	}

	public String toString() {
		return new String(value);
	}
}
