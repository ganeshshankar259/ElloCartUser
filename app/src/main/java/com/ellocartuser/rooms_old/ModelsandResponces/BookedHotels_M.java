package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookedHotels_M {

    @SerializedName("rsub_title")
    @Expose
    private String rsubTitle;
    @SerializedName("rusb_city")
    @Expose
    private String rusbCity;
    @SerializedName("rchild_main")
    @Expose
    private String rchildMain;
    @SerializedName("rchild_title_s")
    @Expose
    private String rchildTitleS;
    @SerializedName("rsub_image1")
    @Expose
    private String rusbImage1;
    @SerializedName("b_orderid")
    @Expose
    private String b_orderid;
    @SerializedName("b_reason_msg")
    @Expose
    private String b_reason_msg;

    public BookedHotels_M(String rsubTitle, String rusbCity, String rchildMain, String rchildTitleS, String rusbImage1,String b_orderid,String b_reason_msg) {
        super();
        this.rsubTitle = rsubTitle;
        this.rusbCity = rusbCity;
        this.rchildMain = rchildMain;
        this.rchildTitleS = rchildTitleS;
        this.rusbImage1 = rusbImage1;
        this.b_orderid = b_orderid;
        this.b_reason_msg = b_reason_msg;

    }

    public String getRsubTitle() {
        return rsubTitle;
    }

    public void setRsubTitle(String rsubTitle) {
        this.rsubTitle = rsubTitle;
    }

    public String getRusbCity() {
        return rusbCity;
    }

    public void setRusbCity(String rusbCity) {
        this.rusbCity = rusbCity;
    }

    public String getRchildMain() {
        return rchildMain;
    }

    public void setRchildMain(String rchildMain) {
        this.rchildMain = rchildMain;
    }

    public String getRchildTitleS() {
        return rchildTitleS;
    }

    public void setRchildTitleS(String rchildTitleS) {
        this.rchildTitleS = rchildTitleS;
    }

    public String getRusbImage1() {
        return rusbImage1;
    }

    public void setRusbImage1(String rusbImage1) {
        this.rusbImage1 = rusbImage1;
    }
    public String getb_orderid() {
        return b_orderid;
    }

    public void setb_orderid(String b_orderid) {
        this.b_orderid = b_orderid;
    }
    public String getb_reason_msg() {
        return b_reason_msg;
    }

    public void setb_reason_msg(String b_reason_msg) {
        this.b_reason_msg = b_reason_msg;
    }
}
