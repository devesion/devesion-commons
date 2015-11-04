package com.devesion.commons.utils.types;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SecureRandomGeneratorTest {

	@DataProvider(name = "stringLengthsGenerator")
	public Object[][] createStringLengths() {
		return new Object[][] {
				{1},
				{2},
				{4},
				{10},
				{16},
				{20},
				{50},
				{100},
				{1000},
				{100000}
		};
	}

	@Test(dataProvider = "stringLengthsGenerator")
	public void generateRandomStringShouldReturnStringWithExactLength(int expectedLength) throws Exception {
		// given

		// when
		String generatedString = SecureRandomGenerator.generateRandomString(expectedLength);

		// then
		assertThat(generatedString.length()).isEqualTo(expectedLength);
	}

	@DataProvider(name = "stringLengthsGeneratorForLevenshtein")
	public Object[][] createStringLengthsForLevenshtein() {
		return new Object[][] {
				{20},
				{50},
				{100},
				{1000}
		};
	}

	@Test(dataProvider = "stringLengthsGeneratorForLevenshtein")
	public void generateRandomStringShouldReturnRandomChars(int expectedLength) throws Exception {
		// given
		String lastGeneratedString = "";

		// when
		for (int i = 0; i < 100; i++) {
			String generatedString = SecureRandomGenerator.generateRandomString(expectedLength);
			int levenshteinDistance = StringUtils.getLevenshteinDistance(generatedString, lastGeneratedString);
			lastGeneratedString = generatedString;
			assertThat(levenshteinDistance).isGreaterThan((int) (expectedLength * 0.5));
		}

		// then
	}
}