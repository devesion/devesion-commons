package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.ws.rs.core.Response;

@Slf4j
public class CorsPreflightInterceptor extends AbstractPhaseInterceptor<Message> {

	public CorsPreflightInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		String methodName = message.get(Message.HTTP_REQUEST_METHOD).toString();

		if (methodName.equalsIgnoreCase("options")) {
			Response response = Response.ok().build();
			message.getExchange().put(Response.class, response);
		}
	}
}
