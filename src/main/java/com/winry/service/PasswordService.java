package com.winry.service;

import com.winry.dto.MyPublicKey;
import com.winry.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class PasswordService {

    @Value("${encrypt}")
    private boolean needEncryption;

    @Autowired
    private PublicKeyService publicKeyService;

    /**
     * plain password is also accepted, encryption can be disabled in .prop file.
     *
     * @param password
     * @return
     */
    public String encrypt(String password) {
        if (needEncryption) {
            MyPublicKey myPublicKey = publicKeyService.getPublicKey();
            PublicKey publicKey = EncryptionUtil.getPublicKey(myPublicKey.getKey());
            password = EncryptionUtil.encrypt(myPublicKey.getHash() + password, publicKey);
        }
        return password;
    }
}
