package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rooms_M {
    @SerializedName("rchild_id")
    @Expose
    private String rchildId;
    @SerializedName("rchild_main")
    @Expose
    private String rchildMain;
    @SerializedName("rchild_title")
    @Expose
    private String rchildTitle;
    @SerializedName("rchild_total")
    @Expose
    private String rchildTotal;
    @SerializedName("rchild_totalper")
    @Expose
    private String rchildTotalper;
    @SerializedName("rchild_checkin")
    @Expose
    private String rchildCheckin;
    @SerializedName("rchild_checkout")
    @Expose
    private String rchildCheckout;
    @SerializedName("rchild_desc")
    @Expose
    private String rchildDesc;
    @SerializedName("rchild_price")
    @Expose
    private String rchildPrice;
    @SerializedName("rchid_htime")
    @Expose
    private String rchidHtime;
    @SerializedName("rchild_image1")
    @Expose
    private String rchildImage1;
    @SerializedName("rchild_status")
    @Expose
    private String rchildStatus;
    @SerializedName("rchild_created")
    @Expose
    private String rchildCreated;

    public Rooms_M(String rchildId, String rchildMain, String rchildTitle, String rchildTotal, String rchildTotalper, String rchildCheckin, String rchildCheckout, String rchildDesc, String rchildPrice, String rchidHtime, String rchildImage1, String rchildStatus, String rchildCreated) {
        super();
        this.rchildId = rchildId;
        this.rchildMain = rchildMain;
        this.rchildTitle = rchildTitle;
        this.rchildTotal = rchildTotal;
        this.rchildTotalper = rchildTotalper;
        this.rchildCheckin = rchildCheckin;
        this.rchildCheckout = rchildCheckout;
        this.rchildDesc = rchildDesc;
        this.rchildPrice = rchildPrice;
        this.rchidHtime = rchidHtime;
        this.rchildImage1 = rchildImage1;
        this.rchildStatus = rchildStatus;
        this.rchildCreated = rchildCreated;
    }

    public String getRchildId() {
        return rchildId;
    }

    public void setRchildId(String rchildId) {
        this.rchildId = rchildId;
    }

    public String getRchildMain() {
        return rchildMain;
    }

    public void setRchildMain(String rchildMain) {
        this.rchildMain = rchildMain;
    }

    public String getRchildTitle() {
        return rchildTitle;
    }

    public void setRchildTitle(String rchildTitle) {
        this.rchildTitle = rchildTitle;
    }

    public String getRchildTotal() {
        return rchildTotal;
    }

    public void setRchildTotal(String rchildTotal) {
        this.rchildTotal = rchildTotal;
    }

    public String getRchildTotalper() {
        return rchildTotalper;
    }

    public void setRchildTotalper(String rchildTotalper) {
        this.rchildTotalper = rchildTotalper;
    }

    public String getRchildCheckin() {
        return rchildCheckin;
    }

    public void setRchildCheckin(String rchildCheckin) {
        this.rchildCheckin = rchildCheckin;
    }

    public String getRchildCheckout() {
        return rchildCheckout;
    }

    public void setRchildCheckout(String rchildCheckout) {
        this.rchildCheckout = rchildCheckout;
    }

    public String getRchildDesc() {
        return rchildDesc;
    }

    public void setRchildDesc(String rchildDesc) {
        this.rchildDesc = rchildDesc;
    }

    public String getRchildPrice() {
        return rchildPrice;
    }

    public void setRchildPrice(String rchildPrice) {
        this.rchildPrice = rchildPrice;
    }

    public String getRchidHtime() {
        return rchidHtime;
    }

    public void setRchidHtime(String rchidHtime) {
        this.rchidHtime = rchidHtime;
    }

    public String getRchildImage1() {
        return rchildImage1;
    }

    public void setRchildImage1(String rchildImage1) {
        this.rchildImage1 = rchildImage1;
    }

    public String getRchildStatus() {
        return rchildStatus;
    }

    public void setRchildStatus(String rchildStatus) {
        this.rchildStatus = rchildStatus;
    }

    public String getRchildCreated() {
        return rchildCreated;
    }

    public void setRchildCreated(String rchildCreated) {
        this.rchildCreated = rchildCreated;
    }
}
