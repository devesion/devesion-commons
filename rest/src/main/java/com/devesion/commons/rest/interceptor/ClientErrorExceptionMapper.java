package com.devesion.commons.rest.interceptor;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ClientErrorExceptionMapper implements ExceptionMapper<ClientErrorException> {

	@Override
	public Response toResponse(ClientErrorException exception) {
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
