package com.devesion.commons.ddd.repository;

import com.google.common.base.Optional;

import javax.validation.Valid;

/**
 * Unicast DDD repository for single object with class {@link T}.
 */
public interface UnicastRepository<T> {

	T find();

	Optional<T> findOptional();

	void save(@Valid T object);

	void delete();
}
