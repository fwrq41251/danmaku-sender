package com.winry.util;

import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionUtil {

    public static final String ALGORITHM = "RSA";

    public static String encrypt(String text, PublicKey key) {
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(String raw) {
        try {
            byte[] bytes = Base64.getDecoder().decode(raw.getBytes("utf-8"));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
