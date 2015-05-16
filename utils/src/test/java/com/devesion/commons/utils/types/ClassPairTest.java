package com.devesion.commons.utils.types;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for {@link ClassPair}.
 */
public class ClassPairTest {

	@Test
	public void shouldMakeProperClassPair() {
		// given
		Class<String> classSrc = String.class;
		Class<Number> classDst = Number.class;

		// when
		ClassPair<String, Number> classPair = ClassPair.newClassPair(classSrc, classDst);

		// then
		assertThat(classPair.getClassSrc()).isEqualTo(classSrc);
		assertThat(classPair.getClassDst()).isEqualTo(classDst);
	}

	@DataProvider(name = "invalidClassProvider")
	public Object[][] createInvalidClass() {
		return new Object[][] {
				{null, null},
				{String.class, null},
				{null, String.class},
		};
	}

	@Test(dataProvider = "invalidClassProvider")
	public void shouldNotMakeClassPairAndThrowException(Class<?> classSrc, Class<?> classDst) {
		// given
		Exception caughtException = null;

		// when
		try {
			ClassPair.newClassPair(classSrc, classDst);
		} catch(Exception e) {
			caughtException = e;
		}

		// then
		assertThat(caughtException).isExactlyInstanceOf(NullPointerException.class);
	}
}

