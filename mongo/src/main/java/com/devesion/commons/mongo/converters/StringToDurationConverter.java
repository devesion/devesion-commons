package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.Duration;

@ReadingConverter
public class StringToDurationConverter implements Converter<String, Duration> {

	@Override
	public final Duration convert(String source) {
		return Duration.parse(source);
	}
}
