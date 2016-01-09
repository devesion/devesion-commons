package com.devesion.commons.mapper;

public interface Mapper {

	<F, T> void map(F from, T to);

	<F, T> T map(F from, Class<T> clazz);
}
