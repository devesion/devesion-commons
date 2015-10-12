package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.JsonMappingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

	@Override
	public Response toResponse(JsonMappingException exception) {
		log.info("json mapping error - {}", exception);
		return Response.status(Response.Status.BAD_REQUEST).entity("json parse error").build();
	}
}
