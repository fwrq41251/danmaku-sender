package com.winry.dto;

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

    @Override
    public String toString() {
        return "PublicKey [hash=" + hash + ", key=" + key + "]";
    }

}
