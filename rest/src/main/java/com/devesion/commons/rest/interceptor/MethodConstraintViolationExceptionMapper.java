package com.devesion.commons.rest.interceptor;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

import java.util.Set;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MethodConstraintViolationExceptionMapper implements ExceptionMapper<MethodConstraintViolationException> {

	@Override
	public Response toResponse(MethodConstraintViolationException exception) {
		Set<MethodConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		StringBuilder violationMessageBuilder = new StringBuilder("constraint violations: ");

		for (MethodConstraintViolation<?> constraintViolation : constraintViolations) {
			violationMessageBuilder
					.append("[param: ")
					.append(constraintViolation.getPropertyPath())
					.append(", cause: ")
					.append(constraintViolation.getMessage())
					.append("] ");
		}

		String violationMessage = violationMessageBuilder.toString();
		return Response.status(Response.Status.BAD_REQUEST).entity(violationMessage).build();
	}
}
