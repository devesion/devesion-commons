package com.devesion.commons.ddd.specification.compiler.statement;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents simple logical statement which consists of operator and two operands.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class SimpleStatement extends Statement<Object> {

	public SimpleStatement(Operator operator, Object operand1, Object operand2) {
		super(operator, operand1, operand2);
	}
}
