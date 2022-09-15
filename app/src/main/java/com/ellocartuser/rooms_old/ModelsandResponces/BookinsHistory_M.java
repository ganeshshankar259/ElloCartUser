package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookinsHistory_M {

    @SerializedName("b_id ")
    @Expose
    private String bId;
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
    @SerializedName("b_date")
    @Expose
    private String bDate;
    @SerializedName("b_childqty")
    @Expose
    private String bChildqty;
    @SerializedName("rchild_price")
    @Expose
    private String rchildPrice;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("rchild_image1")
    @Expose
    private String rchild_image1;
    @SerializedName("b_orderid")
    @Expose
    private String b_orderid;

    public BookinsHistory_M(String bId, String rsubTitle, String rusbCity, String rchildMain, String rchildTitleS, String bDate, String bChildqty, String rchildPrice, Integer totalAmount, String rchild_image1,String b_orderid) {
        super();
        this.bId = bId;
        this.rsubTitle = rsubTitle;
        this.rusbCity = rusbCity;
        this.rchildMain = rchildMain;
        this.rchildTitleS = rchildTitleS;
        this.bDate = bDate;
        this.bChildqty = bChildqty;
        this.rchildPrice = rchildPrice;
        this.totalAmount = totalAmount;
        this.rchild_image1 = rchild_image1;
        this.b_orderid = b_orderid;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
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

    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    public String getbChildqty() {
        return bChildqty;
    }

    public void setbChildqty(String bChildqty) {
        this.bChildqty = bChildqty;
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

    public String getRusbImage1() {
        return rchild_image1;
    }

    public void setRusbImage1(String rchild_image1) {
        this.rchild_image1 = rchild_image1;
    }

    public String getb_orderid() {
        return b_orderid;
    }

    public void setb_orderid(String b_orderid) {
        this.b_orderid = b_orderid;
    }
}
