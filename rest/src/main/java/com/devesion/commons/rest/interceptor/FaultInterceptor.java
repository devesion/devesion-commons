package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

@Slf4j
public class FaultInterceptor extends AbstractPhaseInterceptor<Message> {

	public FaultInterceptor() {
		super(Phase.MARSHAL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		Throwable throwable = message.getContent(Exception.class).getCause();
		Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
		if (throwable instanceof NoSuchMethodError) {
			status = Response.Status.NOT_FOUND;
		}

		HttpServletResponse response = (HttpServletResponse) message.getExchange()
				.getInMessage()
				.get(AbstractHTTPDestination.HTTP_RESPONSE);

		response.setStatus(status.getStatusCode());
		message.getInterceptorChain().abort();
	}
}
