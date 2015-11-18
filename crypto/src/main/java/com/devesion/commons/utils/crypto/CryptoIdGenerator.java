package com.devesion.commons.utils.crypto;

import com.devesion.commons.utils.types.uuid.TimeUUIDGen;

import java.util.concurrent.ThreadLocalRandom;

public final class CryptoIdGenerator {

	private CryptoIdGenerator() {
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
		Key key = Key.randomKey();
		Message message = Message.fromBytes(randomString.getBytes());
		SimpleCipher cipher = new AesCipher();
		Message encryptedMessage = cipher.encrypt(message, key);
		return encryptedMessage.toBase64();
	}

	private static String filterBase64(String base64String) {
		return base64String.replaceAll("[^a-zA-Z0-9]", "");
	}
}