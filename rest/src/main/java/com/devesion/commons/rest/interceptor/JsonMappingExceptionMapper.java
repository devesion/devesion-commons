package com.devesion.commons.rest.interceptor;

import org.codehaus.jackson.map.JsonMappingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity("json parse error").build();
	}
}
