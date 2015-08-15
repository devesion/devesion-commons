package com.devesion.commons.utils.cipher;

public interface Cipher {
	byte[] encrypt(byte[] raw, String key);

	byte[] decrypt(byte[] raw, String key);
}
