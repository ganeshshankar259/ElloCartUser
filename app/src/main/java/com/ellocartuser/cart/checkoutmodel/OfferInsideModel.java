package com.ellocartuser.cart.checkoutmodel;

import com.google.gson.annotations.SerializedName;

public class OfferInsideModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;
    @SerializedName("selected")
    private String selected;
    @SerializedName("id")
    private String id;
    @SerializedName("amt")
    private String amt;
    @SerializedName("title")
    private String title;
    @SerializedName("time")
    private String time;
    @SerializedName("img")
    private String img;
    @SerializedName("img2")
    private String img2;

    public OfferInsideModel(String msg, String status, String selected, String id, String amt, String title, String time, String img, String img2) {
        this.msg = msg;
        this.status = status;
        this.selected = selected;
        this.id = id;
        this.amt = amt;
        this.title = title;
        this.time = time;
        this.img = img;
        this.img2 = img2;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }
}
