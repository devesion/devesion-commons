package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.Instant;

@WritingConverter
public class InstantToStringConverter implements Converter<Instant, String> {

	@Override
	public final String convert(Instant source) {
		return source.toString();
	}
}
