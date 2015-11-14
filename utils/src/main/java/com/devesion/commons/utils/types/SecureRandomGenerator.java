package com.devesion.commons.utils.types;

import java.security.SecureRandom;

public final class SecureRandomGenerator {

	private SecureRandomGenerator() {
	}

	public static String generateRandomString(int length) {
		byte[] randomBytes = new byte[length / 2];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(randomBytes);

		StringBuilder sb = new StringBuilder();
		for (byte randomByte : randomBytes) {
			sb.append(createCharacter(Math.abs(randomByte >> 4)));
			sb.append(createCharacter(Math.abs(randomByte & 0x0F)));
		}

		sb.setLength(length);
		return sb.toString();
	}

	private static char createCharacter(int asciiIndex) {
		return (char) ('a' + asciiIndex);
	}
}
