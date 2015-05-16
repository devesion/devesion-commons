package com.devesion.commons.ddd.specification.property.name;

import com.devesion.commons.ddd.specification.property.PropertySpecification;
import lombok.ToString;

/**
 * Name specification.
 */
@ToString(callSuper = true)
public class NameSpecification extends PropertySpecification<String, String> {

	private static final String DEFAULT_NAME = "name";

	private NameSpecification() {
		super(DEFAULT_NAME);
	}

	public NameSpecification(String name) {
		super(name);
	}

	public static NameSpecification name() {
		return new NameSpecification();
	}

	public static NameSpecification name(String name) {
		return new NameSpecification(name);
	}
}
