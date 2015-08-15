package com.devesion.commons.ddd.repository;

import com.google.common.base.Optional;

public interface IdEntity<K> {

	Optional<K> getIdOptional();

	void setId(String id);
}
