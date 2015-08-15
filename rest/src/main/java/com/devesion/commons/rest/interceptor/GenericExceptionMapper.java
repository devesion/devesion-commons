package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		log.error("internal server error {}", exception);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
