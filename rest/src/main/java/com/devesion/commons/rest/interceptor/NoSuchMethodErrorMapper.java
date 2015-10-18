package com.devesion.commons.rest.interceptor;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoSuchMethodErrorMapper implements ExceptionMapper<NoSuchMethodException> {

	@Override
	public Response toResponse(NoSuchMethodException exception) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
