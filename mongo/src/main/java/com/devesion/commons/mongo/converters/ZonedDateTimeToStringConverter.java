package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.ZonedDateTime;

@WritingConverter
public class ZonedDateTimeToStringConverter implements Converter<ZonedDateTime, String> {

	@Override
	public final String convert(ZonedDateTime source) {
		return source.toString();
	}
}
