package com.devesion.commons.ddd.repository;

import com.devesion.commons.ddd.specification.Specification;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Standard DDD repository for objects with class {@link T} and keys with class {@link K}.
 */
public interface StandardRepository<T, K> extends SimpleStandardRepository<T, K> {

	T findBySpecification(Specification specification);

	Optional<T> findBySpecificationOptional(Specification specification);

	List<T> findAllBySpecification(Specification specification);
}
