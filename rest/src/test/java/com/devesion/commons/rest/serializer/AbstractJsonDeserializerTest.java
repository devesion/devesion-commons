package com.devesion.commons.rest.serializer;

import com.devesion.commons.ddd.shared.InternalException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Tests for {@link AbstractJsonDeserializer}.
 */
public class AbstractJsonDeserializerTest {

	@Mock
	private JsonParser jsonParserMock;

	@Mock
	private DeserializationContext deserializationContextMock;

	private AbstractJsonDeserializer sut;

	@BeforeMethod
	private void beforeMethod() {
		initMocks(this);
	}

	@Test
	public final void deserializeShouldWrapExceptionWhenAnyIsThrown() throws Exception {
		// given
		sut = new ExceptionThrowingAbstractJsonDeserializerFake();
		Exception caughtException = null;

		// when
		try {
			sut.deserialize(jsonParserMock, deserializationContextMock);
		} catch (Exception e) {
			caughtException = e;
		}

		// then
		assertThat(caughtException).isNotNull().isExactlyInstanceOf(InternalException.class);
	}

	@Test
	public final void deserializeShouldDelegateToInternalDeserialize() throws Exception {
		// given
		Object expectedValue = "value";
		sut = new AbstractJsonDeserializerFake(expectedValue);

		// when
		Object returnedObject = sut.deserialize(jsonParserMock, deserializationContextMock);

		// then
		assertThat(returnedObject).isEqualTo(expectedValue);
	}

	private static final class AbstractJsonDeserializerFake extends AbstractJsonDeserializer<Object> {

		private final Object returnObject;

		private AbstractJsonDeserializerFake(Object returnObject) {
			this.returnObject = returnObject;
		}

		@Override
		protected Object internalDeserialize(JsonParser jp) {
			return returnObject;
		}
	}

	private static class ExceptionThrowingAbstractJsonDeserializerFake extends AbstractJsonDeserializer<Object> {
		@Override
		protected final Object internalDeserialize(JsonParser jp) throws IOException {
			throw new IOException("some exception");
		}
	}
}