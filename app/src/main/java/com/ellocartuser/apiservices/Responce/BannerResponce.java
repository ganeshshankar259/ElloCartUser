package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.apiservices.model.HomeOrders;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponce {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("banners")
    @Expose
    private List<Banners> banners;


    @SerializedName("banners2")
    @Expose
    private List<Banners> banners2;

    @SerializedName("banners3")
    @Expose
    private List<Banners> banners3;

    @SerializedName("orders")
    @Expose
    private List<HomeOrders> orders;

    public BannerResponce(String status, String message, List<Banners> banners, List<Banners> banners2, List<Banners> banners3, List<HomeOrders> orders) {
        this.status = status;
        this.message = message;
        this.banners = banners;
        this.banners2 = banners2;
        this.banners3 = banners3;
        this.orders = orders;
    }

    public List<Banners> getBanners3() {
        return banners3;
    }

    public void setBanners3(List<Banners> banners3) {
        this.banners3 = banners3;
    }

    public List<Banners> getBanners2() {
        return banners2;
    }

    public void setBanners2(List<Banners> banners2) {
        this.banners2 = banners2;
    }

    public List<HomeOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<HomeOrders> orders) {
        this.orders = orders;
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

    public List<Banners> getBanners() {
        return banners;
    }

    public void setBanners(List<Banners> banners) {
        this.banners = banners;
    }
}
