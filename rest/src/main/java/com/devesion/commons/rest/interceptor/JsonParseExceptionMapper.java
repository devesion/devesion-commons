package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.JsonParseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

	@Override
	public Response toResponse(JsonParseException exception) {
		log.info("json parse error - {}", exception);
		return Response.status(Response.Status.BAD_REQUEST).entity("json parse error").build();
	}
}
