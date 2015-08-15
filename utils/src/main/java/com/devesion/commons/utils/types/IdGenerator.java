package com.devesion.commons.utils.types;

import com.devesion.commons.utils.cipher.AesCipher;
import com.devesion.commons.utils.cipher.Cipher;
import com.devesion.commons.utils.types.uuid.TimeUUIDGen;
import com.google.common.io.BaseEncoding;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public final class IdGenerator {

	private static final SecureRandom secureRandomGenerator = new SecureRandom();
	private static final Cipher cipher = new AesCipher();

	private IdGenerator() {
	}

	public static String generate() {
		String randomString = generateRandomString();
		String encryptedString = generateEncryptedString(randomString);
		return filterBase64(encryptedString);
	}

	private static String generateRandomString() {
		return generateUUID() + generateNonce();
	}

	private static String generateUUID() {
		return TimeUUIDGen.buildUniqueUUID().toString();
	}

	private static String generateNonce() {
		Long nonce = ThreadLocalRandom.current().nextLong();
		return nonce.toString();
	}

	private static String generateEncryptedString(String randomString) {
		String key = generateKey();
		byte[] encryptedRandom = cipher.encrypt(randomString.getBytes(), key);
		return BaseEncoding.base64().encode(encryptedRandom);
	}

	private static String generateKey() {
		double secureRandom = secureRandomGenerator.nextDouble();
		return Long.toHexString(Double.doubleToLongBits(secureRandom));
	}

	private static String filterBase64(String base64String) {
		return base64String.replaceAll("[^a-zA-Z0-9]", "");
	}
}