package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotels_M {
    @SerializedName("rsub_id")
    @Expose
    private String rsubId;
    @SerializedName("rsub_main")
    @Expose
    private String rsubMain;
    @SerializedName("rsub_title")
    @Expose
    private String rsubTitle;
    @SerializedName("rsub_image1")
    @Expose
    private String rsubImage1;
    @SerializedName("rsub_city")
    @Expose
    private String rusbCity;

    public Hotels_M(String rsubId, String rsubMain, String rsubTitle, String rsubImage1, String rusbCity) {
        super();
        this.rsubId = rsubId;
        this.rsubMain = rsubMain;
        this.rsubTitle = rsubTitle;
        this.rsubImage1 = rsubImage1;
        this.rusbCity = rusbCity;
    }

    public String getRsubId() {
        return rsubId;
    }

    public void setRsubId(String rsubId) {
        this.rsubId = rsubId;
    }

    public String getRsubMain() {
        return rsubMain;
    }

    public void setRsubMain(String rsubMain) {
        this.rsubMain = rsubMain;
    }

    public String getRsubTitle() {
        return rsubTitle;
    }

    public void setRsubTitle(String rsubTitle) {
        this.rsubTitle = rsubTitle;
    }

    public String getRusbImage1() {
        return rsubImage1;
    }

    public void setRusbImage1(String rusbImage1) {
        this.rsubImage1 = rusbImage1;
    }

    public String getRusbCity() {
        return rusbCity;
    }

    public void setRusbCity(String rusbCity) {
        this.rusbCity = rusbCity;
    }

}
