package com.devesion.commons.mapper;

import ma.glasnost.orika.Converter;

import java.util.List;

/**
 * Factory for mappers.
 */
public interface MapperFactory {

	Mapper createMapper();

	Mapper createMapper(List<Converter<?, ?>> converters);
}
