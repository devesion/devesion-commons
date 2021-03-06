package com.devesion.commons.utils.crypto;

public interface SimpleCipher {

	Message encrypt(Message message, Key key);

	Message encrypt(Message message, Key key, InitializationVector iv);

	Message decrypt(Message message, Key key);

	Message decrypt(Message message, Key key, InitializationVector iv);
}
