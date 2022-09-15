package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Coupons;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CouponResponce {



    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("coupons")
    @Expose
    public List<Coupons> coupons;

    public CouponResponce(String status, String message, List<Coupons> coupons) {
        this.status = status;
        this.message = message;
        this.coupons = coupons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Coupons> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupons> coupons) {
        this.coupons = coupons;
    }
}
