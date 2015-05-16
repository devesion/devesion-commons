package com.devesion.commons.camel;

/**
 * Interfejs odbiorcy wiadomości.
 */
public interface DynamicRecipient<T> {
	void receive(T message);
}
