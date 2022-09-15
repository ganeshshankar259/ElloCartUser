package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCatResponce {

    @SerializedName("subcategories")
    public List<SubcategoryStoreAll> subcategories = null;
    @SerializedName("category_name")
    public String categoryName;
    @SerializedName("status")
    @Expose
    public  String status;
    @SerializedName("message")
    public String message;
    @SerializedName("type")
    public String type;
    @SerializedName("seller_id")
    public String sellerId;
    @SerializedName("category_id")
    public String categoryId;

    public SubCatResponce(List<SubcategoryStoreAll> subcategories, String categoryName, String status, String message, String type, String sellerId, String categoryId) {
        this.subcategories = subcategories;
        this.categoryName = categoryName;
        this.status = status;
        this.message = message;
        this.type = type;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
    }

    public List<SubcategoryStoreAll> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryStoreAll> subcategories) {
        this.subcategories = subcategories;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
