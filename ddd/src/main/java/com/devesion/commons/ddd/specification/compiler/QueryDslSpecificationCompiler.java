package com.devesion.commons.ddd.specification.compiler;

import com.devesion.commons.ddd.specification.Specification;
import com.devesion.commons.ddd.specification.composite.AndSpecification;
import com.devesion.commons.ddd.specification.composite.NotSpecification;
import com.devesion.commons.ddd.specification.composite.OrSpecification;
import com.devesion.commons.ddd.specification.order.OrderSpecification;
import com.devesion.commons.ddd.specification.property.ComparablePropertySpecification;
import com.devesion.commons.ddd.specification.property.PropertySpecification;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryDslSpecificationCompiler implements SpecificationCompiler<Predicate, OrderSpecifier> {

	@Getter
	private Class<?> candidate;

	public QueryDslSpecificationCompiler(Class<?> candidate) {
		this.candidate = candidate;
	}

	@Override
	public Predicate compileAnd(AndSpecification specification) {
		BooleanBuilder bb = new BooleanBuilder();
		for (Specification operand : specification.getOperands()) {
			Predicate p = operand.compile(this);
			bb.and(p);
		}

		return bb.getValue();
	}

	@Override
	public Predicate compileOr(OrSpecification specification) {
		BooleanBuilder bb = new BooleanBuilder();
		for (Specification operand : specification.getOperands()) {
			Predicate p = operand.compile(this);
			bb.or(p);
		}

		return bb.getValue();
	}

	@Override
	public Predicate compileNot(NotSpecification specification) {
		Specification operand = specification.getFirst();
		Predicate p = operand.compile(this);
		return p.not();
	}

	@Override
	public Predicate compileProperty(PropertySpecification specification) {
		Object name = specification.getName();
		Object value = specification.getValue();
		PathBuilder<?> entityPath = new PathBuilder<>(candidate, "candidate");
		return entityPath.get(name.toString()).eq(Expressions.constant(value));
	}

	@Override
	public Predicate compileComparableProperty(ComparablePropertySpecification specification) {
		Object name = specification.getName();
		Object value = specification.getValue();
		PathBuilder<?> entityPath = new PathBuilder<>(candidate, "candidate");
		NumberPath path = entityPath.getNumber(name.toString(), Long.class);
		return path.gt((Long) value);
		//.eq(Expressions.constant(value));
	}

	@Override
	public OrderSpecifier compileOrder(OrderSpecification specification) {
		Object name = specification.getName();
		PathBuilder<?> entityPath = new PathBuilder<>(candidate, "candidate");
		Expression expression = entityPath.get(name.toString());
		Order order = specification.isAsc() ? Order.ASC : Order.DESC;
		return new OrderSpecifier(order, expression);
	}
}
