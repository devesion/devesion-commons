package com.devesion.commons.ddd.specification.property.date;

import com.devesion.commons.ddd.specification.property.ComparablePropertySpecification;
import lombok.ToString;

import java.util.Date;

/**
 * Date specification.
 */
@ToString(callSuper = true)
public class DateSpecification extends ComparablePropertySpecification<String, Date> {

	private static final String DEFAULT_NAME = "date";

	private DateSpecification() {
		super(DEFAULT_NAME);
	}

	public DateSpecification(String name) {
		super(name);
	}

	public static DateSpecification date() {
		return new DateSpecification();
	}

	public static DateSpecification date(String name) {
		return new DateSpecification(name);
	}
}
