package com.ellocartuser.cart.checkoutmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TipsModel {


    @SerializedName("p1")
    private String p1;
    @SerializedName("p2")
    private List<TipsInsideModel> p2 = null;

    public TipsModel(String p1, List<TipsInsideModel> p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public List<TipsInsideModel> getP2() {
        return p2;
    }

    public void setP2(List<TipsInsideModel> p2) {
        this.p2 = p2;
    }
}
