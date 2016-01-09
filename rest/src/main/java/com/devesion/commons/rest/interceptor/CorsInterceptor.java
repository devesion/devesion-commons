package com.devesion.commons.rest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CorsInterceptor extends AbstractPhaseInterceptor<Message> {

	public CorsInterceptor() {
		super(Phase.MARSHAL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		if (message == null) {
			return;
		}

		Map<String, List<String>> headers = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		if (headers == null) {
			headers = new HashMap<>();
		}

		headers.put("Access-Control-Allow-Origin", Collections.singletonList("*"));
		headers.put("Access-Control-Allow-Methods", Collections.singletonList("GET, POST, PUT, DELETE"));
		headers.put("Access-Control-Allow-Headers", Collections.singletonList("accept, content-type, x-user-login, x-user-password, x-user-token"));
		message.put(Message.PROTOCOL_HEADERS, headers);
	}
}
