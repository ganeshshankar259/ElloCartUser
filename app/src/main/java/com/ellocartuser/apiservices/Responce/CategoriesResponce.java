package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.apiservices.model.Categories;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.apiservices.model.TrendStore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponce {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("categories_clr")
    @Expose
    private String categories_clr;

    @SerializedName("categories_h1")
    @Expose
    private String categories_h1;

    @SerializedName("categories_h2")
    @Expose
    private String categories_h2;

    @SerializedName("categories")
    @Expose
    private List<Categories> categories;

    @SerializedName("status_2")
    @Expose
    private String status_2;

    @SerializedName("categories_2_clr")
    @Expose
    private String categories_2_clr;

    @SerializedName("categories_2_h1")
    @Expose
    private String categories_2_h1;

    @SerializedName("categories_2_h2")
    @Expose
    private String categories_2_h2;


    //all india
    @SerializedName("categories_2")
    @Expose
    private List<Categories> categories_2;


    @SerializedName("status_3")
    @Expose
    private String status_3;

    @SerializedName("categories_3_clr")
    @Expose
    private String categories_3_clr;

    @SerializedName("categories_3")
    @Expose
    private List<Categories> categories_3;

    //down section
    @SerializedName("categories_4_clr2")
    @Expose
    private String category_4_clr1;

    @SerializedName("categories_4_clr")
    @Expose
    private String category_4_clr;

    //offer section
    @SerializedName("categories_4")
    @Expose
    private List<Stores> categories_4;

    //only in service detail
    @SerializedName("banners")
    @Expose
    private List<Banners> banners;

    @SerializedName("servcat_status")
    @Expose
    private String servcat_status;

    //trending
    @SerializedName("stores")
    @Expose
    private List<TrendStore> stores;

    public CategoriesResponce(String status, String message, String type, String categories_clr, String categories_h1, String categories_h2, List<Categories> categories, String status_2, String categories_2_clr, String categories_2_h1, String categories_2_h2, List<Categories> categories_2, String status_3, String categories_3_clr, List<Categories> categories_3, String category_4_clr1, String category_4_clr, List<Stores> categories_4, List<Banners> banners, String servcat_status, List<TrendStore> stores) {
        this.status = status;
        this.message = message;
        this.type = type;
        this.categories_clr = categories_clr;
        this.categories_h1 = categories_h1;
        this.categories_h2 = categories_h2;
        this.categories = categories;
        this.status_2 = status_2;
        this.categories_2_clr = categories_2_clr;
        this.categories_2_h1 = categories_2_h1;
        this.categories_2_h2 = categories_2_h2;
        this.categories_2 = categories_2;
        this.status_3 = status_3;
        this.categories_3_clr = categories_3_clr;
        this.categories_3 = categories_3;
        this.category_4_clr1 = category_4_clr1;
        this.category_4_clr = category_4_clr;
        this.categories_4 = categories_4;
        this.banners = banners;
        this.servcat_status = servcat_status;
        this.stores = stores;
    }

    public String getCategories_2_h1() {
        return categories_2_h1;
    }

    public void setCategories_2_h1(String categories_2_h1) {
        this.categories_2_h1 = categories_2_h1;
    }

    public String getCategories_2_h2() {
        return categories_2_h2;
    }

    public void setCategories_2_h2(String categories_2_h2) {
        this.categories_2_h2 = categories_2_h2;
    }

    public String getCategories_h1() {
        return categories_h1;
    }

    public void setCategories_h1(String categories_h1) {
        this.categories_h1 = categories_h1;
    }

    public String getCategories_h2() {
        return categories_h2;
    }

    public void setCategories_h2(String categories_h2) {
        this.categories_h2 = categories_h2;
    }

    public String getCategories_3_clr() {
        return categories_3_clr;
    }

    public void setCategories_3_clr(String categories_3_clr) {
        this.categories_3_clr = categories_3_clr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategories_clr() {
        return categories_clr;
    }

    public void setCategories_clr(String categories_clr) {
        this.categories_clr = categories_clr;
    }

    public String getStatus_2() {
        return status_2;
    }

    public void setStatus_2(String status_2) {
        this.status_2 = status_2;
    }

    public String getCategories_2_clr() {
        return categories_2_clr;
    }

    public void setCategories_2_clr(String categories_2_clr) {
        this.categories_2_clr = categories_2_clr;
    }

    public String getStatus_3() {
        return status_3;
    }

    public void setStatus_3(String status_3) {
        this.status_3 = status_3;
    }

    public String getCategory_4_clr1() {
        return category_4_clr1;
    }

    public void setCategory_4_clr1(String category_4_clr1) {
        this.category_4_clr1 = category_4_clr1;
    }

    public String getCategory_4_clr() {
        return category_4_clr;
    }

    public void setCategory_4_clr(String category_4_clr) {
        this.category_4_clr = category_4_clr;
    }

    public List<Stores> getCategories_4() {
        return categories_4;
    }

    public void setCategories_4(List<Stores> categories_4) {
        this.categories_4 = categories_4;
    }

    public List<Categories> getCategories_3() {
        return categories_3;
    }

    public void setCategories_3(List<Categories> categories_3) {
        this.categories_3 = categories_3;
    }


    public List<Categories> getCategories_2() {
        return categories_2;
    }

    public void setCategories_2(List<Categories> categories_2) {
        this.categories_2 = categories_2;
    }

    public List<TrendStore> getStores() {
        return stores;
    }

    public void setStores(List<TrendStore> stores) {
        this.stores = stores;
    }

    public String getServcat_status() {
        return servcat_status;
    }

    public void setServcat_status(String servcat_status) {
        this.servcat_status = servcat_status;
    }

    public List<Banners> getBanners() {
        return banners;
    }

    public void setBanners(List<Banners> banners) {
        this.banners = banners;
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

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
