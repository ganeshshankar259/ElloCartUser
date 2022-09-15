package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeOrders {

    @SerializedName("p1")
    @Expose
    private String p1;

    @SerializedName("p2")
    @Expose
    private String p2;

    public HomeOrders(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
