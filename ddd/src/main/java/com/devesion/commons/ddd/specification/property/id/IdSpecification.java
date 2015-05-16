package com.devesion.commons.ddd.specification.property.id;

import com.devesion.commons.ddd.specification.property.PropertySpecification;
import lombok.ToString;

/**
 * Id specification.
 */
@ToString(callSuper = true)
public class IdSpecification<V> extends PropertySpecification<Object, V> {

	private static final String DEFAULT_NAME = "id";

	protected IdSpecification() {
		super(DEFAULT_NAME);
	}

	public IdSpecification(Object name) {
		super(name);
	}

	public static <V> IdSpecification<V> id() {
		return new IdSpecification<>();
	}

	public static <V> IdSpecification<V> id(Object name) {
		return new IdSpecification<>(name);
	}
}
