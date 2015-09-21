package com.devesion.commons.rest.interceptor;

import org.codehaus.jackson.JsonParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

	@Override
	public Response toResponse(JsonParseException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity("json parse error").build();
	}
}
