package com.devesion.commons.rest.serializer;

import com.devesion.commons.ddd.shared.InternalException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for {@link AbstractJsonSerializer}.
 */
public class AbstractJsonSerializerTest {

	@Mock
	private JsonGenerator jsonGeneratorMock;

	@Mock
	private SerializerProvider serializerProviderMock;

	private AbstractJsonSerializer<Object> sut;

	@BeforeMethod
	private void beforeMethod() {
		initMocks(this);
	}

	@Test
	public final void serializeShouldWrapExceptionWhenIsThrown() throws Exception {
		// given
		sut = new ExceptionThrowingAbstractJsonSerializerFake();
		Object value = "test_value";
		Exception caughtException = null;

		// when
		try {
			sut.serialize(value, jsonGeneratorMock, serializerProviderMock);
		} catch (Exception e) {
			caughtException = e;
		}

		// then
		assertThat(caughtException).isNotNull().isExactlyInstanceOf(InternalException.class);
	}

	@Test
	public final void serializeShouldDelegateToInternalSerialize() throws Exception {
		// given
		Object expectedValue = "value";
		AbstractJsonSerializerFake abstractJsonSerializerFake = new AbstractJsonSerializerFake();
		sut = abstractJsonSerializerFake;

		// when
		sut.serialize(expectedValue, jsonGeneratorMock, serializerProviderMock);

		// then
		assertThat(abstractJsonSerializerFake.passedValue).isEqualTo(expectedValue);
		assertThat(abstractJsonSerializerFake.passedJGen).isEqualTo(jsonGeneratorMock);
	}

	private static class AbstractJsonSerializerFake extends AbstractJsonSerializer<Object> {

		private Object passedValue;

		private JsonGenerator passedJGen;

		@Override
		protected final void internalSerialize(Object value, JsonGenerator jgen) {
			passedValue = value;
			passedJGen = jgen;
		}
	}

	private static class ExceptionThrowingAbstractJsonSerializerFake extends AbstractJsonSerializer<Object> {

		@Override
		protected final void internalSerialize(Object value, JsonGenerator jgen) throws IOException {
			throw new IOException("some exception");
		}
	}
}