package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Banners {

    @SerializedName("banner_id")
    @Expose
    private String banner_id;

    @SerializedName("banner_page")
    @Expose
    private String banner_page;

    @SerializedName("banner_name")
    @Expose
    private String banner_name;

    @SerializedName("banner_image")
    @Expose
    private String banner_image;


    @SerializedName("banner_all")
    @Expose
    private String banner_all;


    @SerializedName("servcat_id")
    @Expose
    private String servcat_id;

    @SerializedName("seller_id")
    @Expose
    private String seller_id;

    @SerializedName("product_id")
    @Expose
    private String product_id;
    @SerializedName("servcat_name")
    @Expose
    private String servcat_name;
    @SerializedName("servcat_image")
    @Expose
    private String servcat_image;


    public Banners(String banner_id, String banner_page, String banner_name, String banner_image, String banner_all, String servcat_id, String seller_id, String product_id, String servcat_name, String servcat_image) {
        this.banner_id = banner_id;
        this.banner_page = banner_page;
        this.banner_name = banner_name;
        this.banner_image = banner_image;
        this.banner_all = banner_all;
        this.servcat_id = servcat_id;
        this.seller_id = seller_id;
        this.product_id = product_id;
        this.servcat_name = servcat_name;
        this.servcat_image = servcat_image;
    }

    public String getBanner_all() {
        return banner_all;
    }

    public void setBanner_all(String banner_all) {
        this.banner_all = banner_all;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_page() {
        return banner_page;
    }

    public void setBanner_page(String banner_page) {
        this.banner_page = banner_page;
    }

    public String getBanner_name() {
        return banner_name;
    }

    public void setBanner_name(String banner_name) {
        this.banner_name = banner_name;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getServcat_id() {
        return servcat_id;
    }

    public void setServcat_id(String servcat_id) {
        this.servcat_id = servcat_id;
    }

    public String getServcat_name() {
        return servcat_name;
    }

    public void setServcat_name(String servcat_name) {
        this.servcat_name = servcat_name;
    }

    public String getServcat_image() {
        return servcat_image;
    }

    public void setServcat_image(String servcat_image) {
        this.servcat_image = servcat_image;
    }
}
