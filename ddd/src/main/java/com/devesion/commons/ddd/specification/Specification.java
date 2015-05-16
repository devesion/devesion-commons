package com.devesion.commons.ddd.specification;

import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.google.common.base.Optional;

/**
 * Specification interface.
 */
@com.devesion.commons.annotations.ddd.Specification
public interface Specification {

	boolean isSatisfiedBy(Object candidate);

	<T, O> T compile(SpecificationCompiler<T, O> compiler);

	<T, O> Optional<O> compileOrder(SpecificationCompiler<T, O> compiler);
}
