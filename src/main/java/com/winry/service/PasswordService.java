package com.winry.service;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.winry.dto.MyPublicKey;
import com.winry.util.EncryptionUtil;

@Service
public class PasswordService {

	@Value("${encrypt}")
	private boolean needEncryption;

	@Autowired
	private PublicKeyService publicKeyService;

	public String encrypt(String password) {
		if (needEncryption) {
			MyPublicKey myPublicKey = publicKeyService.getPublicKey();
			PublicKey publicKey = EncryptionUtil.getPublicKey(myPublicKey.getRawKey());
			password = EncryptionUtil.encrypt(myPublicKey.getHash() + password, publicKey);
		}
		return password;
	}
}
