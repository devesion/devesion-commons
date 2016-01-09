package com.devesion.commons.mapper.orika;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Duration;

@Slf4j
class DurationToLongMapper extends BidirectionalConverter<Duration, Long> {

	private static final long DEFAULT_DURATION = 0;

	@Override
	public Duration convertFrom(Long source, Type<Duration> destinationType) {
		return (source != null) ? Duration.ofSeconds(source) : Duration.ofSeconds(0);
	}

	@Override
	public Long convertTo(Duration source, Type<Long> destinationType) {
		return (source != null) ? source.getSeconds() : DEFAULT_DURATION;
	}
}
