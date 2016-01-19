package com.devesion.commons.rest.interceptor;

import com.devesion.commons.ddd.shared.ObjectNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ObjectNotFoundExceptionMapper implements ExceptionMapper<ObjectNotFoundException> {

	@Override
	public Response toResponse(ObjectNotFoundException exception) {
		return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
	}
}
