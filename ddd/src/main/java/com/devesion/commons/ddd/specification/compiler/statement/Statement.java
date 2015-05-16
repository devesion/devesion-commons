package com.devesion.commons.ddd.specification.compiler.statement;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * Skeletal implementation for logical statement.
 */
@Data
public abstract class Statement<T> {

	private final Operator operator;
	private List<T> operands;

	public Statement(Operator operator) {
		this.operator = operator;
		this.operands = Lists.newArrayList();
	}

	public Statement(Operator operator, T ... operands) {
		this.operator = operator;
		this.operands = Lists.newArrayList(operands);
	}

	public Statement(Operator operator, List<T> operands) {
		this.operator = operator;
		this.operands = operands;
	}

	public void addNextOperand(T operand) {
		operands.add(operand);
	}

	public void addOperand(int index, T operand) {
		operands.add(index, operand);
	}

	public T getFirstOperand() {
		return getOperandByIndex(0);
	}

	public T getSecontOperand() {
		return getOperandByIndex(1);
	}

	private T getOperandByIndex(int index) {
		return operands.get(index);
	}
}
