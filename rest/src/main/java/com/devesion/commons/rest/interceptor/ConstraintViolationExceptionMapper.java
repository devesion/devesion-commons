package com.devesion.commons.rest.interceptor;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		StringBuilder violationMessageBuilder = new StringBuilder("constraint violations: ");

		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
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
