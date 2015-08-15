package com.devesion.commons.rest.serializer;

import org.codehaus.jackson.JsonGenerator;

import java.io.IOException;
import java.util.Date;

/**
 * {@link Date} JSON serializer.
 */
public class DateSerializer extends AbstractJsonSerializer<Date> {

	@Override
	protected final void internalSerialize(Date value, JsonGenerator jgen) throws IOException {
		jgen.writeString(getDateString(value));
	}

	private String getDateString(Date value) {
		Long time = value.getTime();
		return time.toString();
	}
}
