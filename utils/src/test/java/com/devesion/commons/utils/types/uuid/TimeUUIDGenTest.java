package com.devesion.commons.utils.types.uuid;

import org.testng.annotations.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for {@link com.devesion.commons.utils.types.uuid.TimeUUIDGen}.
 */
public class TimeUUIDGenTest {

	public static final int EXPECTED_VARIANT = 2;
	public static final int NEGATIVE_INT = -100;

	@Test
	public void shouldGenerateProperUUIDWithCurrentTimestamp() {
		// given
		long currentTime = System.currentTimeMillis();

		shouldGenerateProperUUID(currentTime);
	}

	@Test
	public void shouldGenerateUUIDWithZeroTimestamp() {
		// given
		long currentTime = 0;

		shouldGenerateProperUUID(currentTime);
	}

	private void shouldGenerateProperUUID(long currentTime) {
		// when
		UUID uuid = TimeUUIDGen.buildUUID(currentTime);

		// then
		assertThat(uuid.timestamp()).isEqualTo(currentTime);
		assertThat(uuid.variant()).isEqualTo(EXPECTED_VARIANT);
	}

	@Test
	public void shouldThrowException() {
		// given
		long currentTime = NEGATIVE_INT;
		Throwable caughtException = null;

		// when
		try {
		TimeUUIDGen.buildUUID(currentTime);
		} catch (Throwable ex) {
			caughtException = ex;
		}

		// then
		assertThat(caughtException).isInstanceOf(RuntimeException.class);
	}
}
