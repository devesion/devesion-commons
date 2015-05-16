package com.devesion.commons.ddd.shared;

import com.devesion.commons.ddd.specification.Specification;

/**
 * Represents situation when there is no required object with given id or for given specification.
 */
public class ObjectNotFoundException extends ContractException {

	private static final String THERE_IS_NO_OBJECT = "there is no object";

	/**
	 * The key of not found object.
	 */
	private Object key;

	/**
	 * The specification for not found object.
	 */
	private Specification specification;

	public ObjectNotFoundException(Object key) {
		this.key = key;
	}

	protected ObjectNotFoundException(Specification specification) {
		this.specification = specification;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append(THERE_IS_NO_OBJECT);

		if (key != null) {
			sb.append(" with key '");
			sb.append(key);
			sb.append("'");
		} else
		if (specification != null) {
			sb.append(" for given specification '");
			sb.append(specification);
			sb.append("'");
		}

		return sb.toString();
	}
}
