package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.Specification;

/**
 * Composite specification interface.
 */
@com.devesion.commons.annotations.ddd.Specification
public interface CompositeSpecification extends Specification {

	/**
	 * Adds segment specifications to the composite and binds it with "and" operator.
	 */
	CompositeSpecification and(Specification... specifications);

	/**
	 * Adds segment specifications to the composite and binds it with "or" operator.
	 */
	CompositeSpecification or(Specification... specifications);

	/**
	 * Adds negation of segment.
	 */
	CompositeSpecification not();
}
