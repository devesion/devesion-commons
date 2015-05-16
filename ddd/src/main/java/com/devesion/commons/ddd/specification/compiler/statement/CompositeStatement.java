package com.devesion.commons.ddd.specification.compiler.statement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Represents complex logical statement which consists of operator and list of operands.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class CompositeStatement extends Statement<Statement<?>> {

	public CompositeStatement(Operator operator) {
		super(operator);
	}

	public CompositeStatement(Operator operator, List<Statement<?>> operands) {
		super(operator, operands);
	}
}
