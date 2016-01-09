package com.devesion.commons.mapper;

import com.devesion.commons.ddd.shared.UnknownException;

public class MapperException extends UnknownException {

	private static final long serialVersionUID = 1L;

	public MapperException(String message) {
		super(message);
	}
}
