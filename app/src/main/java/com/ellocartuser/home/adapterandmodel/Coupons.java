package com.ellocartuser.home.adapterandmodel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  Coupons {

    @SerializedName("coup_id")
    @Expose
    public String coupId;
    @SerializedName("coup_name")
    @Expose
    public String coupName;

    @SerializedName("coup_title")
    @Expose
    public String coup_title;

    @SerializedName("coup_amount")
    @Expose
    public Integer coupAmount;
    @SerializedName("coup_image")
    @Expose
    public String coup_image;
    @SerializedName("coup_desc")
    @Expose
    public String coup_desc;

    @SerializedName("coup_banner")
    @Expose
    public String coup_banner;

    @SerializedName("coup_status")
    @Expose
    public String coup_status;
    @SerializedName("coup_msg")
    @Expose
    public String coup_msg;

    public Coupons(String coupId, String coupName, String coup_title, Integer coupAmount, String coup_image, String coup_desc, String coup_banner, String coup_status, String coup_msg) {
        this.coupId = coupId;
        this.coupName = coupName;
        this.coup_title = coup_title;
        this.coupAmount = coupAmount;
        this.coup_image = coup_image;
        this.coup_desc = coup_desc;
        this.coup_banner = coup_banner;
        this.coup_status = coup_status;
        this.coup_msg = coup_msg;
    }

    public String getCoup_title() {
        return coup_title;
    }

    public void setCoup_title(String coup_title) {
        this.coup_title = coup_title;
    }

    public String getCoup_status() {
        return coup_status;
    }

    public void setCoup_status(String coup_status) {
        this.coup_status = coup_status;
    }

    public String getCoup_msg() {
        return coup_msg;
    }

    public void setCoup_msg(String coup_msg) {
        this.coup_msg = coup_msg;
    }

    public String getCoup_banner() {
        return coup_banner;
    }

    public void setCoup_banner(String coup_banner) {
        this.coup_banner = coup_banner;
    }

    public String getCoupId() {
        return coupId;
    }

    public void setCoupId(String coupId) {
        this.coupId = coupId;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public Integer getCoupAmount() {
        return coupAmount;
    }

    public void setCoupAmount(Integer coupAmount) {
        this.coupAmount = coupAmount;
    }

    public String getCoup_image() {
        return coup_image;
    }

    public void setCoup_image(String coup_image) {
        this.coup_image = coup_image;
    }

    public String getCoup_desc() {
        return coup_desc;
    }

    public void setCoup_desc(String coup_desc) {
        this.coup_desc = coup_desc;
    }
}