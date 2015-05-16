package com.devesion.commons.utils.types;

import com.devesion.commons.annotations.Immutable;
import com.devesion.commons.annotations.ddd.ValueObject;
import com.devesion.commons.annotations.patterns.FactoryMethod;
import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Binds two related classes.
 */
@ValueObject
@Immutable
@EqualsAndHashCode
public final class ClassPair<F, T> {

	/**
	 * Source class.
	 */
	@Getter
	private final Class<F> classSrc;

	/**
	 * Destination class.
	 */
	@Getter
	private final Class<T> classDst;

	private ClassPair(Class<F> classSrc, Class<T> classDst) {
		Preconditions.checkNotNull(classSrc);
		Preconditions.checkNotNull(classDst);
		this.classSrc = classSrc;
		this.classDst = classDst;
	}

	@FactoryMethod
	public static <F, T> ClassPair<F, T> newClassPair(Class<F> classSrc, Class<T> classDst) {
		return new ClassPair<>(classSrc, classDst);
	}
}
