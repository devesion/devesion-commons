package com.devesion.commons.mapper.orika;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Slf4j
class InstantToDateTimeMapper extends BidirectionalConverter<Instant, ZonedDateTime> {

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	@Override
	public final Instant convertFrom(ZonedDateTime source, Type<Instant> destinationType) {
		return (source != null) ? source.toInstant() : Instant.now();
	}

	@Override
	public final ZonedDateTime convertTo(Instant source, Type<ZonedDateTime> destinationType) {
		return (source != null) ? ZonedDateTime.ofInstant(source, ZONE_OFFSET) : ZonedDateTime.now(ZONE_OFFSET);
	}
}
