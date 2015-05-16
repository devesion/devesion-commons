package com.devesion.commons.ddd.specification;

import com.google.common.base.Predicate;

import javax.annotation.Nullable;

public class SpecificationPredicate<T> implements Predicate<T> {
	private Specification specification;

	private SpecificationPredicate(Specification specification) {
		this.specification = specification;
	}

	public static <T> SpecificationPredicate<T> createFromSpecification(Specification specification) {
		return new SpecificationPredicate<>(specification);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean apply(@Nullable T input) {
		return specification.isSatisfiedBy(input);
	}
}