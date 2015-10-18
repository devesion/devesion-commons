package com.devesion.commons.rest.interceptor;

import com.devesion.commons.ddd.shared.ContractException;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

@Slf4j
public class ContractExceptionMapper implements ExceptionMapper<ContractException> {

	@Override
	public Response toResponse(ContractException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity("contract violation").build();
	}
}
