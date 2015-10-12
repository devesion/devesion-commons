package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class UnrecognizedFieldExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException> {

	@Override
	public Response toResponse(UnrecognizedPropertyException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity("json parse error - unrecognized field '" + exception.getUnrecognizedPropertyName() + "'").build();
	}
}
