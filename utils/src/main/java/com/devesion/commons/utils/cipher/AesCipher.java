package com.devesion.commons.utils.cipher;

import com.devesion.commons.ddd.shared.InternalException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

public class AesCipher implements Cipher {

	private static final String CIPHER_ALGORITHM = "AES";
	private static final String HASH_ALGORITHM = "SHA-1";
	private static final String SALT = "09182309174016501754021705620640218401981296421562196512391204824238142715120752150151292163402146012472146759";
	private static final int MAX_KEY_LENGTH = 16;

	@Override
	public final byte[] encrypt(byte[] raw, String key) {
		javax.crypto.Cipher cipher;
		try {
			cipher = javax.crypto.Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, getKeyBytes(key));
			return cipher.doFinal(raw);
		} catch (GeneralSecurityException e) {
			throw new InternalException("cannot encrypt cursor", e);
		}
	}

	@Override
	public final byte[] decrypt(byte[] raw, String key) {
		javax.crypto.Cipher cipher;
		try {
			cipher = javax.crypto.Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, getKeyBytes(key));
			return cipher.doFinal(raw);
		} catch (GeneralSecurityException e) {
			throw new InternalException("cannot decrypt cursor", e);
		}
	}

	private SecretKeySpec getKeyBytes(String key) {
		try {
			byte[] rawKey = (SALT + key).getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);
			rawKey = sha.digest(rawKey);
			rawKey = Arrays.copyOf(rawKey, MAX_KEY_LENGTH);
			return new SecretKeySpec(rawKey, CIPHER_ALGORITHM);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new InternalException("cannot generate hashed key", e);
		}
	}
}
