package com.devesion.commons.ddd.specification.order;

import com.devesion.commons.ddd.specification.AbstractSpecification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.google.common.base.Optional;
import lombok.Getter;

public class OrderSpecification<N> extends AbstractSpecification {

	@Getter
	private N name;

	@Getter
	private boolean asc = true;

	public OrderSpecification(N name) {
		this.name = name;
	}

	public static <N, V> OrderSpecification<N> order(N name) {
		return new OrderSpecification<>(name);
	}

	public OrderSpecification<N> desc() {
		this.asc = false;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Object candidate) {
		return false;
	}

	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, O> Optional<O> compileOrder(SpecificationCompiler<T, O> compiler) {
		O order = compiler.compileOrder(this);
		return Optional.fromNullable(order);
	}
}
