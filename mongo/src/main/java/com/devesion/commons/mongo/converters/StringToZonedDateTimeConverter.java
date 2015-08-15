package com.devesion.commons.mongo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.ZonedDateTime;

@ReadingConverter
public class StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {

	@Override
	public final ZonedDateTime convert(String source) {
		return ZonedDateTime.parse(source);
	}
}
