package com.devesion.commons.utils.crypto;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class AbstractCipher implements SimpleCipher {

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	@Override
	public Message decrypt(Message message, Key key) {
		InitializationVector iv = InitializationVector.emptyVector();
		return decrypt(message, key, iv);
	}

	@Override
	public Message encrypt(Message message, Key key) {
		InitializationVector iv = InitializationVector.emptyVector();
		return encrypt(message, key, iv);
	}

	@Override
	public Message decrypt(Message message, Key key, InitializationVector iv) {
		try {
			SecretKeySpec keySpec = createSecretKeySpec(key);
			IvParameterSpec ivSpec = createIvSpec(iv);
			Cipher cipher = createCipher();

			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			return processMessage(message, cipher);
		} catch (GeneralSecurityException e) {
			throw new CryptoException("encryption failed", e);
		}
	}

	@Override
	public Message encrypt(Message message, Key key, InitializationVector iv) {
		try {
			SecretKeySpec keySpec = createSecretKeySpec(key);
			IvParameterSpec ivSpec = createIvSpec(iv);
			Cipher cipher = createCipher();

			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			return processMessage(message, cipher);
		} catch (GeneralSecurityException e) {
			throw new CryptoException("encryption failed", e);
		}
	}

	private SecretKeySpec createSecretKeySpec(Key key) {
		return new SecretKeySpec(key.getBytes(), getCipherName());
	}

	private IvParameterSpec createIvSpec(InitializationVector iv) {
		return new IvParameterSpec(iv.getBytes());
	}

	private Cipher createCipher() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		return Cipher.getInstance(getCipherMode(), "BC");
	}

	private Message processMessage(Message message, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
		byte[] encryptedValue = cipher.doFinal(message.toBytes());
		return Message.fromBytes(encryptedValue);
	}

	protected abstract String getCipherName();

	protected abstract String getCipherMode();
}
