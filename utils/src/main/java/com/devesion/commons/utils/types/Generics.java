package com.devesion.commons.utils.types;

import java.lang.reflect.ParameterizedType;

/**
 * Dostarcza operacje upraszczające współpracę z typami generycznymi.
 */
public class Generics {

	private static final int DEFAULT_INDEX = 0;

	@SuppressWarnings("unchecked")
	public static Class<?> getTypeParameter(Class<?> clazz) {
		return getTypeParameter(clazz, DEFAULT_INDEX);
	}

	public static Class<?> getTypeParameter(Class<?> clazz, int index) {
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		return (Class<?>) type.getActualTypeArguments()[index];
	}
}
