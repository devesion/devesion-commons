package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.Specification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents "not" predicate.
 */
@ToString
public class NotSpecification extends AbstractCompositeSpecification {

	@Getter
	private Specification first;

	public NotSpecification(Specification first) {
		this.first = first;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Object candidate) {
		return !first.isSatisfiedBy(candidate);
	}

	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return compiler.compileNot(this);
	}
}
