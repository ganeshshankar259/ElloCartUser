package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class OtherCharge {

    @SerializedName("p0")
    private String p0;
    @SerializedName("p1")
    private String p1;
    @SerializedName("p2")
    private String p2;
    @SerializedName("p3")
    private String p3;

    @SerializedName("p4")
    private String p4;


    public OtherCharge(String p0, String p1, String p2, String p3, String p4) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public String getP0() {
        return p0;
    }

    public void setP0(String p0) {
        this.p0 = p0;
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

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }
}
