package com.devesion.commons.mapper.orika;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

import java.time.Instant;

class InstantToInstantMapper extends CustomConverter<Instant, Instant> {

	@Override
	public final Instant convert(Instant source, Type<? extends Instant> destinationType) {
		return (source != null) ? source : Instant.now();
	}
}
