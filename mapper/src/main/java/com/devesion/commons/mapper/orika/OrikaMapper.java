package com.devesion.commons.mapper.orika;

import com.devesion.commons.mapper.Mapper;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

import java.util.Collection;

/**
 * Mapper implementation based on Orika.
 */
class OrikaMapper implements Mapper {

	private final MapperFacade mapperFacade;

	OrikaMapper() {
		this.mapperFacade = OrikaMapperFieldHolder.mapperFactory.getMapperFacade();
	}

	OrikaMapper(Collection<Converter<?, ?>> converters) {
		MapperFactory mapperFactory = OrikaMapperFieldHolder.mapperFactory;
		ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converters.stream().forEach(converterFactory::registerConverter);
		this.mapperFacade = mapperFactory.getMapperFacade();
	}

	@Override
	public final <F, T> void map(F from, T to) {
		mapperFacade.map(from, to);
	}

	@Override
	public final <F, T> T map(F from, Class<T> clazz) {
		return mapperFacade.map(from, clazz);
	}

	static final class OrikaMapperFieldHolder {

		static {
			OrikaFacadeFactory facadeFactory = new OrikaFacadeFactory();
			mapperFactory = facadeFactory.createFacade();
		}

		static final MapperFactory mapperFactory;

		private OrikaMapperFieldHolder() {
		}
	}

	private static class OrikaFacadeFactory {

		private MapperFactory createFacade() {
			DefaultMapperFactory mapperFactory = new Builder().useAutoMapping(true).mapNulls(true).build();
			ConverterFactory converterFactory = mapperFactory.getConverterFactory();
			converterFactory.registerConverter(new InstantToInstantMapper());
			converterFactory.registerConverter(new InstantToDateTimeMapper());
			converterFactory.registerConverter(new InstantToDateMapper());
			converterFactory.registerConverter(new ZonedDateTimeToDateMapper());
			converterFactory.registerConverter(new DurationToLongMapper());
			return mapperFactory;
		}
	}
}
