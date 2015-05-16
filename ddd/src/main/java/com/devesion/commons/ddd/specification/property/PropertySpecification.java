package com.devesion.commons.ddd.specification.property;

import com.devesion.commons.ddd.specification.AbstractSpecification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Object's attribute specification.
 */
@ToString
@Slf4j
public class PropertySpecification<N, V> extends AbstractSpecification {

	@Getter
	private N name;

	@Getter
	private V value;

	protected PropertySpecification() {
	}

	public PropertySpecification(N name) {
		this.name = name;
	}

	public static <N, V> PropertySpecification<N, V> property() {
		return new PropertySpecification<>();
	}

	public static <N, V> PropertySpecification<N, V> property(N name) {
		return new PropertySpecification<>(name);
	}

	public PropertySpecification<N, V> is(V value) {
		this.value = value;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSatisfiedBy(Object candidate) {
		Object propertyValue = getCandidateProperty(candidate, name);
		return value.equals(propertyValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return compiler.compileProperty(this);
	}
}
