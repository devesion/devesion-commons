package com.devesion.commons.utils.crypto;

import org.bouncycastle.util.encoders.Base64;
import org.testng.annotations.Test;

import java.security.SecureRandom;

import static org.fest.assertions.Assertions.assertThat;

public class MessageTest {

	@Test
	public void fromBytesShouldCreateNewObjectUsingPassedBytes() throws Exception {
		// given
		byte[] expectedBytes = generateRandomBytes();

		// when
		Message message = Message.fromBytes(expectedBytes);

		// then
		byte[] bytes = message.toBytes();
		assertThat(bytes).isEqualTo(expectedBytes);
	}

	@Test
	public void fromBase64ShouldCreateNewObjectUsingDecodedString() throws Exception {
		// given
		byte[] expectedBytes = generateRandomBytes();
		String base64Value = new String(Base64.encode(expectedBytes));

		// when
		Message message = Message.fromBase64(base64Value);

		// then
		byte[] bytes = message.toBytes();
		assertThat(bytes).isEqualTo(expectedBytes);
	}

	@Test
	public void fromStringShouldCreateNewObjectUsingBytesFromString() throws Exception {
		// given
		String stringValue = "test_string_1234567890_testing_some_string";
		byte[] expectedBytes = stringValue.getBytes();

		// when
		Message message = Message.fromString(stringValue);

		// then
		byte[] bytes = message.toBytes();
		assertThat(bytes).isEqualTo(expectedBytes);
	}

	@Test
	public void toStringShouldCreateStringFromMessageValue() throws Exception {
		// given
		String expectedValue = "test_string_1234567890_testing_some_string";

		// when
		Message message = Message.fromString(expectedValue);

		// then
		String value = message.toString();
		assertThat(value).isEqualTo(expectedValue);
	}

	@Test
	public void toBase64ShouldCreateBase64StringFromEncodedMessageValue() throws Exception {
		// given
		String value = "test_string_1234567890_testing_some_string";
		String expectedValue = Base64.toBase64String(value.getBytes());

		// when
		Message message = Message.fromString(value);
		String base64Value = message.toBase64();

		// then
		assertThat(base64Value).isEqualTo(expectedValue);
	}

	@Test
	public void toBytesShouldReturnBytesFromMessageValue() throws Exception {
		// given
		String value = "test_string_1234567890_testing_some_string";
		byte[] expectedBytes = value.getBytes();

		// when
		Message message = Message.fromString(value);
		byte[] bytes = message.toBytes();

		// then
		assertThat(bytes).isEqualTo(expectedBytes);
	}

	private byte[] generateRandomBytes() {
		byte[] expectedBytes = new byte[100];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(expectedBytes);
		return expectedBytes;
	}
}