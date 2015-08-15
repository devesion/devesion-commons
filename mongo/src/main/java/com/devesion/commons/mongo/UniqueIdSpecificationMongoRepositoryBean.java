package com.devesion.commons.mongo;

import com.devesion.commons.ddd.repository.IdEntity;
import com.devesion.commons.ddd.repository.StandardRepository;
import com.devesion.commons.utils.types.IdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Mongo based {@link StandardRepository} implementation generating unique id.
 */
@Slf4j
public class UniqueIdSpecificationMongoRepositoryBean<T extends IdEntity<K>, K extends Serializable> extends SpecificationMongoRepositoryBean<T, K> {

	@Override
	public T save(T entity) {
		if (!entity.getIdOptional().isPresent()) {
			addUniqueId(entity);
		}

		return super.save(entity);
	}

	private void addUniqueId(T entity) {
		String uniqueId = IdGenerator.generate();
		entity.setId(uniqueId);
	}
}
