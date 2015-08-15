package com.devesion.commons.rest.serializer;

import com.devesion.commons.ddd.shared.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;

import java.io.IOException;

/**
 * Skeletal implementation for {@link JsonSerializer}.
 */
@Slf4j
abstract class AbstractJsonDeserializer<T> extends JsonDeserializer<T> {

	@Override
	public final T deserialize(JsonParser jp, DeserializationContext ctxt) {
		try {
			return internalDeserialize(jp);
		} catch (IOException e) {
			throw new InternalException(JsonSerializerConstants.CANNOT_SERIALIZE_OBJECT, e);
		}
	}

	protected abstract T internalDeserialize(JsonParser jp) throws IOException;
}
