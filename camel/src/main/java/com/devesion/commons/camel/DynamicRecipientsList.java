package com.devesion.commons.camel;

import com.google.common.base.Preconditions;

import java.util.Set;

/**
 * Dynamic recipients lists.
 */
public class DynamicRecipientsList<T> {

	private Set<DynamicRecipient<T>> recipients;

	public DynamicRecipientsList(Set<DynamicRecipient<T>> recipients) {
		this.recipients = recipients;
	}

	public void routeToAll(T message) {
		Preconditions.checkNotNull(message);
		if ((recipients == null) || (recipients.isEmpty())) {
			return;
		}

		for (DynamicRecipient<T> recipient : recipients) {
			recipient.receive(message);
		}
	}
}
