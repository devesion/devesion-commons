package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.Duration;

@WritingConverter
public class DurationToStringConverter implements Converter<Duration, String> {

	@Override
	public final String convert(Duration source) {
		return source.toString();
	}
}
