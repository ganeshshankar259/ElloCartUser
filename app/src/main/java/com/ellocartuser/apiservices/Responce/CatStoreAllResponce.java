package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.CategoryStoreAll;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatStoreAllResponce {


    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("category_name")
    @Expose
    public String categoryName;
    @SerializedName("categories")
    @Expose
    public List<CategoryStoreAll> categories;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("seller_store_name")
    @Expose
    public String seller_store_name;

    public CatStoreAllResponce(String status, String categoryName, List<CategoryStoreAll> categories, String sellerId, String categoryId, String seller_store_name) {
        this.status = status;
        this.categoryName = categoryName;
        this.categories = categories;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.seller_store_name = seller_store_name;
    }

    public String getSeller_store_name() {
        return seller_store_name;
    }

    public void setSeller_store_name(String seller_store_name) {
        this.seller_store_name = seller_store_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryStoreAll> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStoreAll> categories) {
        this.categories = categories;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
