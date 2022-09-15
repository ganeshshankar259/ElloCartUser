package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class TrendStore {

    @SerializedName("seller_id")
    public String sellerId;
    @SerializedName("seller_store_image")
    public String sellerStoreImage;
    @SerializedName("seller_store_name")
    public String sellerStoreName;
    @SerializedName("seller_store_address")
    public String sellerStoreAddress;
    @SerializedName("seller_city")
    public String sellerCity;
    @SerializedName("seller_pincode")
    public String sellerPincode;
    @SerializedName("seller_rating")
    public String sellerRating;
    @SerializedName("seller_lat")
    public String sellerLat;
    @SerializedName("seller_long")
    public String sellerLong;
    @SerializedName("seller_distance")
    public Integer sellerDistance;
    @SerializedName("seller_ostatus")
    public String sellerOstatus;
    @SerializedName("seller_minimum_order")
    public String sellerMinimumOrder;
    @SerializedName("seller_time")
    public String sellerTime;
    @SerializedName("category_id")
    public String categoryId;


    public TrendStore(String sellerId, String sellerStoreImage, String sellerStoreName, String sellerStoreAddress, String sellerCity, String sellerPincode, String sellerRating, String sellerLat, String sellerLong, Integer sellerDistance, String sellerOstatus, String sellerMinimumOrder, String sellerTime, String categoryId) {
        this.sellerId = sellerId;
        this.sellerStoreImage = sellerStoreImage;
        this.sellerStoreName = sellerStoreName;
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerCity = sellerCity;
        this.sellerPincode = sellerPincode;
        this.sellerRating = sellerRating;
        this.sellerLat = sellerLat;
        this.sellerLong = sellerLong;
        this.sellerDistance = sellerDistance;
        this.sellerOstatus = sellerOstatus;
        this.sellerMinimumOrder = sellerMinimumOrder;
        this.sellerTime = sellerTime;
        this.categoryId = categoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerStoreImage() {
        return sellerStoreImage;
    }

    public void setSellerStoreImage(String sellerStoreImage) {
        this.sellerStoreImage = sellerStoreImage;
    }

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }

    public String getSellerStoreAddress() {
        return sellerStoreAddress;
    }

    public void setSellerStoreAddress(String sellerStoreAddress) {
        this.sellerStoreAddress = sellerStoreAddress;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerPincode() {
        return sellerPincode;
    }

    public void setSellerPincode(String sellerPincode) {
        this.sellerPincode = sellerPincode;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getSellerLat() {
        return sellerLat;
    }

    public void setSellerLat(String sellerLat) {
        this.sellerLat = sellerLat;
    }

    public String getSellerLong() {
        return sellerLong;
    }

    public void setSellerLong(String sellerLong) {
        this.sellerLong = sellerLong;
    }

    public Integer getSellerDistance() {
        return sellerDistance;
    }

    public void setSellerDistance(Integer sellerDistance) {
        this.sellerDistance = sellerDistance;
    }

    public String getSellerOstatus() {
        return sellerOstatus;
    }

    public void setSellerOstatus(String sellerOstatus) {
        this.sellerOstatus = sellerOstatus;
    }

    public String getSellerMinimumOrder() {
        return sellerMinimumOrder;
    }

    public void setSellerMinimumOrder(String sellerMinimumOrder) {
        this.sellerMinimumOrder = sellerMinimumOrder;
    }

    public String getSellerTime() {
        return sellerTime;
    }

    public void setSellerTime(String sellerTime) {
        this.sellerTime = sellerTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
