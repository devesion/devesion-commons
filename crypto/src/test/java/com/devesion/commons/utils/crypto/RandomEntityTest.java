package com.devesion.commons.utils.crypto;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class RandomEntityTest {

	private static final int NUMBER_OF_TRIES = 100;

	@DataProvider(name = "randomLengthForDistance")
	public Object[][] createRandomLengthForDistance() {
		return new Object[][] {
				{20},
				{50},
				{100},
				{1000},
				{10000}
		};
	}

	@Test(dataProvider = "randomLengthForDistance")
	public void constructorShouldGenerateRandomBytesWithFixedLength(int randomLengthForDistance) throws Exception {
		// given
		RandomEntity lastRandomEntity = null;

		// when
		for (int i = 0; i < NUMBER_OF_TRIES; i++) {
			RandomEntity randomEntity = new RandomEntity(randomLengthForDistance);
			assertRandomDistance(lastRandomEntity, randomEntity);
			lastRandomEntity = randomEntity;
		}

		// then
	}

	private void assertRandomDistance(RandomEntity lastRandomEntity, RandomEntity randomEntity) {
		if (lastRandomEntity == null) {
			return;
		}

		int distance = 0;
		int randomLength = randomEntity.getLength();
		for (int i = 0; i < randomLength; i++) {
			distance += Math.abs(randomEntity.getByte(i) - lastRandomEntity.getByte(i));
		}

		int maximalDistance = randomLength * 255;
		int expectedDistance = (int) (maximalDistance * 0.1);
		assertThat(distance).isGreaterThan(expectedDistance);
	}
}