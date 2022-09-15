package com.ellocartuser.ellorooms_new.models;

import com.google.gson.annotations.SerializedName;

public class Key {


    @SerializedName("live_key")
    private String liveKey;
    @SerializedName("secret_key")
    private String secretKey;

    public Key(String liveKey, String secretKey) {
        this.liveKey = liveKey;
        this.secretKey = secretKey;
    }

    public String getLiveKey() {
        return liveKey;
    }

    public void setLiveKey(String liveKey) {
        this.liveKey = liveKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
