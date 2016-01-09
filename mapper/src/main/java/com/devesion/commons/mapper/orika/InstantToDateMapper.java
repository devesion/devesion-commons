package com.devesion.commons.mapper.orika;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;
import java.util.Date;

@Slf4j
class InstantToDateMapper extends BidirectionalConverter<Instant, Date> {

	@Override
	public final Instant convertFrom(Date source, Type<Instant> destinationType) {
		return (source != null) ? source.toInstant() : Instant.now();
	}

	@Override
	public final Date convertTo(Instant source, Type<Date> destinationType) {
		return (source != null) ? new Date(source.getEpochSecond()) : new Date();
	}
}
