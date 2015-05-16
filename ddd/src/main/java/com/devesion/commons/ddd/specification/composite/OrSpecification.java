package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.Specification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Represents "or" predicate.
 */
@ToString
public class OrSpecification extends AbstractCompositeSpecification {

	@Getter
	private List<Specification> operands;

	public OrSpecification(Specification first, List<Specification> specification) {
		this.operands = Lists.newArrayList(first);
		operands.addAll(specification);
	}

	public OrSpecification(List<Specification> specifications) {
		this.operands = specifications;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Object candidate) {
		for (Specification operand : operands) {
			if (operand.isSatisfiedBy(candidate)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return compiler.compileOr(this);
	}
}
