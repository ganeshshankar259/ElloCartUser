package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class SellerBanners {

    @SerializedName("bnr_id")
    public String bnr_id;

    @SerializedName("bnr_name")
    public String bnr_name;

    @SerializedName("bnr_img")
    public String bnr_img;

    public SellerBanners(String bnr_id, String bnr_name, String bnr_img) {
        this.bnr_id = bnr_id;
        this.bnr_name = bnr_name;
        this.bnr_img = bnr_img;
    }

    public String getBnr_id() {
        return bnr_id;
    }

    public void setBnr_id(String bnr_id) {
        this.bnr_id = bnr_id;
    }

    public String getBnr_name() {
        return bnr_name;
    }

    public void setBnr_name(String bnr_name) {
        this.bnr_name = bnr_name;
    }

    public String getBnr_img() {
        return bnr_img;
    }

    public void setBnr_img(String bnr_img) {
        this.bnr_img = bnr_img;
    }
}
