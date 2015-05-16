package com.devesion.commons.ddd.repository;

import com.google.common.base.Optional;

/**
 * Simple standard DDD repository for objects with class {@link T} and keys with class {@link K}.
 */
public interface SimpleStandardRepository<T, K> {

	T findById(K id);

	Optional<T> findByIdOptional(K id);

	T save(T object);
}
