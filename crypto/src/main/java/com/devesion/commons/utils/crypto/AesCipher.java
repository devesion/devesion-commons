package com.devesion.commons.utils.crypto;

public class AesCipher extends AbstractCipher {

	protected String getCipherName() {
		return "AES";
	}

	protected String getCipherMode() {
		return "AES/GCM/NoPadding";
	}

	@Override
	protected Integer getCipherKeyLength() {
		return 16;
	}
}
