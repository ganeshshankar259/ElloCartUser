package com.ellocartuser.cart.checkoutmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OffersModel {


    @SerializedName("p1")
    private String p1;
    @SerializedName("p2")
    private List<OfferInsideModel> p2 = null;

    public OffersModel(String p1, List<OfferInsideModel> p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public List<OfferInsideModel> getP2() {
        return p2;
    }

    public void setP2(List<OfferInsideModel> p2) {
        this.p2 = p2;
    }
}
