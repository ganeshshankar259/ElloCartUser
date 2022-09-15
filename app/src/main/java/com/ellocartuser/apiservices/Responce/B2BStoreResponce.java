package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.B2BStore;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class B2BStoreResponce {

    @SerializedName("b2stores")
    public List<B2BStore> b2stores = null;
    @SerializedName("wishlist")
    public List<B2BStore> wishlist = null;
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("b2category_id")
    public String b2categoryId;
    @SerializedName("message")
    public String message;

    public B2BStoreResponce(List<B2BStore> b2stores, List<B2BStore> wishlist, String status, String type, String b2categoryId, String message) {
        this.b2stores = b2stores;
        this.wishlist = wishlist;
        this.status = status;
        this.type = type;
        this.b2categoryId = b2categoryId;
        this.message = message;
    }

    public List<B2BStore> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<B2BStore> wishlist) {
        this.wishlist = wishlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<B2BStore> getB2stores() {
        return b2stores;
    }

    public void setB2stores(List<B2BStore> b2stores) {
        this.b2stores = b2stores;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getB2categoryId() {
        return b2categoryId;
    }

    public void setB2categoryId(String b2categoryId) {
        this.b2categoryId = b2categoryId;
    }
}
