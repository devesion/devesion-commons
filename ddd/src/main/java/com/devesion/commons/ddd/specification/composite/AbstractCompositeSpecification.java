package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.AbstractSpecification;
import com.devesion.commons.ddd.specification.Specification;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Skeletal implementation for composite specification.
 */
public abstract class AbstractCompositeSpecification extends AbstractSpecification implements CompositeSpecification {

	@Override
	public CompositeSpecification and(Specification... specifications) {
		List<Specification> specificationList = Lists.newArrayList(specifications);
		return new AndSpecification(this, specificationList);
	}

	@Override
	public CompositeSpecification or(Specification... specifications) {
		List<Specification> specificationList = Lists.newArrayList(specifications);
		return new OrSpecification(this, specificationList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompositeSpecification not() {
		return new NotSpecification(this);
	}
}
