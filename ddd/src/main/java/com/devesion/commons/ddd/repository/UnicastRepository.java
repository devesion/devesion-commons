package com.devesion.commons.ddd.repository;

import com.google.common.base.Optional;

/**
 * Unicast DDD repository for single object with class {@link T}.
 */
public interface UnicastRepository<T> {

	T find();

	Optional<T> findOptional();

	void save(T object);

	void delete();
}
