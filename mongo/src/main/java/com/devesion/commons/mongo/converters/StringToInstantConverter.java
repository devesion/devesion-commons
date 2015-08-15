package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Instant;

@ReadingConverter
public class StringToInstantConverter implements Converter<String, Instant> {

	@Override
	public final Instant convert(String source) {
		return Instant.parse(source);
	}
}
