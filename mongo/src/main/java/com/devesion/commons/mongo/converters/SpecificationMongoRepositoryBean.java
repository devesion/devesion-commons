package com.devesion.commons.mongo.converters;

import com.devesion.commons.ddd.repository.StandardRepository;
import com.devesion.commons.ddd.shared.ObjectNotFoundException;
import com.devesion.commons.ddd.specification.Specification;
import com.devesion.commons.ddd.specification.compiler.QueryDslSpecificationCompiler;
import com.devesion.commons.utils.types.Generics;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Mongo based {@link StandardRepository} implementation.
 */
@Slf4j
public class SpecificationMongoRepositoryBean<T, K extends Serializable> implements StandardRepository<T, K> {

	@Getter(AccessLevel.PROTECTED)
	@Setter
	private GeneratedMongoRepository<T, K> mongoRepository;

	@Getter(AccessLevel.PROTECTED)
	@Setter
	private MongoTemplate mongoTemplate;

	@Override
	public T findById(K id) {
		T object = mongoRepository.findOne(id);
		if (object == null) {
			throw new ObjectNotFoundException(id);
		}

		return object;
	}

	@Override
	public T findBySpecification(Specification specification) {
		Optional<T> objectOptional = findBySpecificationOptional(specification);
		if (!objectOptional.isPresent()) {
			throw new ObjectNotFoundException(specification);
		}

		return objectOptional.get();
	}

	@Override
	public Optional<T> findBySpecificationOptional(Specification specification) {
		Class<?> clazz = Generics.getTypeParameter(this.getClass());
		QueryDslSpecificationCompiler compiler = new QueryDslSpecificationCompiler(clazz);
		Predicate predicate = specification.compile(compiler);
		T object = mongoRepository.findOne(predicate);
		return Optional.fromNullable(object);
	}

	@Override
	public Optional<T> findByIdOptional(K id) {
		T object = mongoRepository.findOne(id);
		return Optional.fromNullable(object);
	}

	@Override
	public List<T> findAllBySpecification(Specification specification) {
		Class<?> clazz = Generics.getTypeParameter(this.getClass());
		QueryDslSpecificationCompiler compiler = new QueryDslSpecificationCompiler(clazz);
		Predicate predicate = specification.compile(compiler);
		Optional<OrderSpecifier> orderSpecifierOptional = specification.compileOrder(compiler);

		log.info("findAllBySpecification {}, {}", specification, predicate);

		Iterable<T> items;
		if (orderSpecifierOptional.isPresent()) {
			OrderSpecifier orderSpecifier = orderSpecifierOptional.get();
			items = mongoRepository.findAll(predicate, orderSpecifier);
		} else {
			items = mongoRepository.findAll(predicate);
		}

		List<T> itemsList = Lists.newArrayList();
		for (T item : items) {
			itemsList.add(item);
		}

		return itemsList;
	}

	@Override
	public T save(T object) {
		mongoTemplate.save(object);
		return object;
	}
}
