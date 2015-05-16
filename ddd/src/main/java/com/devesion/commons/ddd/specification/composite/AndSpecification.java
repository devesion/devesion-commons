package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.Specification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Represents "and" predicate.
 */
@ToString
public class AndSpecification extends AbstractCompositeSpecification {

	@Getter
	private List<Specification> operands;

	public AndSpecification(Specification first, List<Specification> specification) {
		this.operands = Lists.newArrayList(first);
		operands.addAll(specification);
	}

	public AndSpecification(List<Specification> specifications) {
		this.operands = specifications;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Object candidate) {
		for (Specification operand : operands) {
			if (!operand.isSatisfiedBy(candidate)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return compiler.compileAnd(this);
	}
}
