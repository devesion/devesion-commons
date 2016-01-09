package com.devesion.commons.mapper.orika;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
class ZonedDateTimeToDateMapper extends BidirectionalConverter<ZonedDateTime, Date> {

	private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

	@Override
	public final ZonedDateTime convertFrom(Date source, Type<ZonedDateTime> destinationType) {
		return (source != null) ? ZonedDateTime.ofInstant(source.toInstant(), ZONE_OFFSET) : ZonedDateTime.now(ZONE_OFFSET);
	}

	@Override
	public final Date convertTo(ZonedDateTime source, Type<Date> destinationType) {
		return (source != null) ? Date.from(source.toInstant()) : new Date();
	}
}
