package com.devesion.commons.ddd.specification.property;

import com.devesion.commons.ddd.specification.AbstractSpecification;
import com.devesion.commons.ddd.specification.compiler.SpecificationCompiler;
import com.devesion.commons.ddd.specification.compiler.statement.Operator;
import lombok.Getter;
import lombok.ToString;

/**
 * Object's attribute specification.
 */
@ToString
public class ComparablePropertySpecification<N, V extends Comparable> extends AbstractSpecification {

	private static final Operator DEFAULT_OPERATOR = Operator.EQ;

	@Getter
	private N name;

	@Getter
	private V value;

	@Getter
	private Operator operator = DEFAULT_OPERATOR;

	protected ComparablePropertySpecification() {
	}

	public ComparablePropertySpecification(N name) {
		this.name = name;
	}

	public static <N, V extends Comparable> ComparablePropertySpecification<N, V> property() {
		return new ComparablePropertySpecification<>();
	}

	public static <N, V extends Comparable> ComparablePropertySpecification<N, V> property(N name) {
		return new ComparablePropertySpecification<>(name);
	}

	public ComparablePropertySpecification<N, V> is(V value) {
		this.value = value;
		return this;
	}

	public ComparablePropertySpecification<N, V> is(V value, Operator operator) {
		this.value = value;
		this.operator = operator;
		return this;
	}

	public ComparablePropertySpecification<N, V> gt(V value) {
		return is(value, Operator.GT);
	}

	public ComparablePropertySpecification<N, V> lt(V value) {
		return is(value, Operator.LT);
	}

	public ComparablePropertySpecification<N, V> gte(V value) {
		return is(value, Operator.GTE);
	}

	public ComparablePropertySpecification<N, V> lte(V value) {
		return is(value, Operator.LTE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean isSatisfiedBy(Object candidate) {
		if (!(candidate instanceof Comparable)) {
			throw new ClassCastException("cannot compare values");
		}

		Comparable candidateValue = (Comparable) getCandidateProperty(candidate, name);

		switch (operator) {
			case EQ:
				return value.equals(candidateValue);

			case GT:
				return value.compareTo(candidateValue) < 0;

			case GTE:
				return value.compareTo(candidateValue) <= 0;

			case LT:
				return value.compareTo(candidateValue) > 0;

			case LTE:
				return value.compareTo(candidateValue) >= 0;

		}

		return false;
	}

	@Override
	public <T, O> T compile(SpecificationCompiler<T, O> compiler) {
		return compiler.compileComparableProperty(this);
	}
}
