package com.devesion.commons.ddd.specification;

import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.google.common.base.Optional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Skeletal implementation of composite specification.
 */
public abstract class AbstractSpecification implements Specification {

	protected Object getCandidateProperty(Object candidate, Object property) {
		return getCandidateProperty(candidate, property.toString());
	}

	protected Object getCandidateProperty(Object candidate, String property) {
		Class<?> clazz = candidate.getClass();
		String getterName = getGetterName(property);
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(getterName)) {
				try {
					return m.invoke(candidate);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new SpecificationEvaluationException(e);
				}
			}
		}

		return null;
	}

	private String getGetterName(String property) {
		return "get" + property.substring(0, 1).toUpperCase() + property.substring(1);
	}

	@Override
	public <T, O> Optional<O> compileOrder(SpecificationCompiler<T, O> compiler) {
		return Optional.absent();
	}
}
