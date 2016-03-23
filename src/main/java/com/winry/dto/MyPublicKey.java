package com.winry.dto;

import org.apache.commons.lang.StringUtils;

public class MyPublicKey {

    private String hash;

    private String key;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRawKey() {
        String key = this.key;
        key = StringUtils.remove(key, "\n");
        key = StringUtils.removeStart(key, "-----BEGIN PUBLIC KEY-----");
        key = StringUtils.removeEnd(key, "-----END PUBLIC KEY-----");
        return key;
    }

    @Override
    public String toString() {
        return "PublicKey [hash=" + hash + ", key=" + key + "]";
    }

}
