package com.devesion.commons.rest.serializer;

import org.codehaus.jackson.JsonGenerator;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class DateSerializerTest {

	@Mock
	private JsonGenerator jsonGeneratorMock;

	private DateSerializer sut;

	@BeforeMethod
	private void beforeMethod() {
		initMocks(this);
		sut = new DateSerializer();
	}

	@Test
	public void internalSerializeShouldBuildProperJson() throws Exception {
		// given
		String rawDateTime = "12345";
		Date expectedZonedDateTime = new Date(Long.parseLong(rawDateTime));

		// when
		sut.internalSerialize(expectedZonedDateTime, jsonGeneratorMock);

		// then
		verify(jsonGeneratorMock).writeString(rawDateTime);
	}
}