package com.devesion.commons.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.io.Serializable;

public interface GeneratedMongoRepository<T, K extends Serializable> extends MongoRepository<T, K>, QueryDslPredicateExecutor<T> {

}
