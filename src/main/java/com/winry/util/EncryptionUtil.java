package com.winry.util;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public final class EncryptionUtil {

	private EncryptionUtil() {
	}

	public static final String ALGORITHM = "RSA";

	public static String encrypt(String text, PublicKey key) {
		try {
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] bytes = cipher.doFinal(text.getBytes());
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PublicKey getPublicKey(String raw) {
		try {
			byte[] bytes = decodeRaw(raw);
			X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
			return keyFactory.generatePublic(spec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	private static byte[] decodeRaw(String raw) {
		raw = StringUtils.remove(raw, "\n");
		raw = StringUtils.removeStart(raw, "-----BEGIN PUBLIC KEY-----");
		raw = StringUtils.removeEnd(raw, "-----END PUBLIC KEY-----");
		return Base64.getDecoder().decode(raw.getBytes(Charset.defaultCharset()));
	}

}
