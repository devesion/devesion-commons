package com.devesion.commons.ddd.specification.composite;

import com.devesion.commons.ddd.specification.Specification;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Composite specifications' static factory.
 */
public class CompositeSpecificationFactory {

	public static AndSpecification and(Specification... specifications) {
		return and(Lists.newArrayList(specifications));
	}

	public static AndSpecification and(List<Specification> specifications) {
		return new AndSpecification(specifications);
	}

	public static OrSpecification or(Specification... specifications) {
		return or(Lists.newArrayList(specifications));
	}

	public static OrSpecification or(List<Specification> specifications) {
		return new OrSpecification(specifications);
	}

	public static NotSpecification not(Specification first) {
		return new NotSpecification(first);
	}
}
