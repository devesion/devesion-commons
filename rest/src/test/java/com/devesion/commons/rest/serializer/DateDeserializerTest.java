package com.devesion.commons.rest.serializer;

import org.codehaus.jackson.JsonParser;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class DateDeserializerTest {

	@Mock
	private JsonParser jsonParserMock;

	private DateDeserializer sut;

	@BeforeMethod
	private void beforeMethod() {
		initMocks(this);
		sut = new DateDeserializer();
	}

	@Test
	public void internalDeserializeShouldParseDateTime() throws Exception {
		// given
		String rawDateTime = "123456";
		Mockito.when(jsonParserMock.getText()).thenReturn(rawDateTime);
		Date expectedZonedDateTime = new Date(Long.parseLong(rawDateTime));

		// when
		Date returnedDateTime = sut.internalDeserialize(jsonParserMock);

		// then
		assertThat(returnedDateTime).isEqualTo(expectedZonedDateTime);
	}
}