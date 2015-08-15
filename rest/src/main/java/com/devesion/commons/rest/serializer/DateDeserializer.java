package com.devesion.commons.rest.serializer;

import org.codehaus.jackson.JsonParser;

import java.io.IOException;
import java.util.Date;

/**
 * {@link Date} JSON serializer.
 */
public class DateDeserializer extends AbstractJsonDeserializer<Date> {

	@Override
	protected final Date internalDeserialize(JsonParser jp) throws IOException {
		return new Date(Long.parseLong(jp.getText()));
	}
}
