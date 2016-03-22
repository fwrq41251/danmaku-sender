package com.winry.util;

import java.security.PublicKey;

import javax.crypto.Cipher;

public class EncryptionUtil {

	public static final String ALGORITHM = "RSA";

	public static byte[] encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cipherText;
	}
}
