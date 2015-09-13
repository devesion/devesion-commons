package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.ws.rs.core.MultivaluedMap;

@Slf4j
public class CorsInterceptor extends AbstractPhaseInterceptor<Message> {

	public CorsInterceptor() {
		super(Phase.MARSHAL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		MultivaluedMap<String, Object> headers = (MetadataMap<String, Object>) message.get(Message.PROTOCOL_HEADERS);

		if (headers == null) {
			headers = new MetadataMap<>();
		}

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		headers.add("Access-Control-Allow-Headers", "accept, content-type, x-user-login, x-user-password, x-user-token");
		message.put(Message.PROTOCOL_HEADERS, headers);
		log.info("added header");
	}
}
