package com.devesion.commons.rest.serializer;

import com.devesion.commons.ddd.shared.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

/**
 * Skeletal implementation for {@link JsonSerializer}.
 */
@Slf4j
public abstract class AbstractJsonSerializer<T> extends JsonSerializer<T> {

	@Override
	public final void serialize(T value, JsonGenerator jgen, SerializerProvider provider) {
		try {
			internalSerialize(value, jgen);
		} catch (IOException e) {
			throw new InternalException(JsonSerializerConstants.CANNOT_SERIALIZE_OBJECT, e);
		}
	}

	protected abstract void internalSerialize(T value, JsonGenerator jgen) throws IOException;
}
