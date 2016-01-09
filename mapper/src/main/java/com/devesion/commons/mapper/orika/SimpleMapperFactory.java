package com.devesion.commons.mapper.orika;

import com.devesion.commons.mapper.Mapper;
import com.devesion.commons.mapper.MapperFactory;
import ma.glasnost.orika.Converter;

import java.util.List;

/**
 * Default implementation of {@link com.devesion.commons.mapper.MapperFactory}.
 */
public class SimpleMapperFactory implements MapperFactory {

	@Override
	public final Mapper createMapper() {
		return new OrikaMapper();
	}

	@Override
	public Mapper createMapper(List<Converter<?, ?>> converters) {
		return new OrikaMapper(converters);
	}
}
