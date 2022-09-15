package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cartdisplay_M {

    @SerializedName("bcart_id")
    @Expose
    private String bcartId;
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
    @SerializedName("bcart_qty")
    @Expose
    private String bcartQty;
    @SerializedName("rchild_price")
    @Expose
    private String rchildPrice;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("rchild_image1")
    @Expose
    private String rchildImage1;

    public Cartdisplay_M(String bcartId, String rsubTitle, String rusbCity, String rchildMain, String rchildTitleS, String bcartQty, String rchildPrice, Integer totalAmount, String rchildImage1) {
        super();
        this.bcartId = bcartId;
        this.rsubTitle = rsubTitle;
        this.rusbCity = rusbCity;
        this.rchildMain = rchildMain;
        this.rchildTitleS = rchildTitleS;
        this.bcartQty = bcartQty;
        this.rchildPrice = rchildPrice;
        this.totalAmount = totalAmount;
        this.rchildImage1 = rchildImage1;
    }

    public String getBcartId() {
        return bcartId;
    }

    public void setBcartId(String bcartId) {
        this.bcartId = bcartId;
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

    public String getBcartQty() {
        return bcartQty;
    }

    public void setBcartQty(String bcartQty) {
        this.bcartQty = bcartQty;
    }

    public String getRchildPrice() {
        return rchildPrice;
    }

    public void setRchildPrice(String rchildPrice) {
        this.rchildPrice = rchildPrice;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRchildImage1() {
        return rchildImage1;
    }

    public void setRchildImage1(String rchildImage1) {
        this.rchildImage1 = rchildImage1;
    }
}
