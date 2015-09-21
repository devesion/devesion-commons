package com.devesion.commons.rest.interceptor;

import org.hibernate.validator.method.MethodConstraintViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MethodConstraintViolationExceptionMapper implements ExceptionMapper<MethodConstraintViolationException> {

	@Override
	public Response toResponse(MethodConstraintViolationException exception) {
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
