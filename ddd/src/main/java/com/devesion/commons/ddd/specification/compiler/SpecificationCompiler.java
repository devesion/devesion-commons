package com.devesion.commons.ddd.specification.compiler;

import com.devesion.commons.ddd.specification.composite.AndSpecification;
import com.devesion.commons.ddd.specification.composite.NotSpecification;
import com.devesion.commons.ddd.specification.composite.OrSpecification;
import com.devesion.commons.ddd.specification.order.OrderSpecification;
import com.devesion.commons.ddd.specification.property.ComparablePropertySpecification;
import com.devesion.commons.ddd.specification.property.PropertySpecification;

/**
 * {@link com.devesion.commons.ddd.specification.Specification} compiler.
 */
public interface SpecificationCompiler<T, O> {

	T compileAnd(AndSpecification specification);

	T compileOr(OrSpecification specification);

	T compileNot(NotSpecification specification);

	T compileProperty(PropertySpecification specification);

	T compileComparableProperty(ComparablePropertySpecification specification);

	O compileOrder(OrderSpecification specification);
}
