package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("seller_promo_status")
    private String seller_promo_status;
    @SerializedName("seller_dlv_status")
    private String seller_dlv_status;

    @SerializedName("seller_promo_eligible")
    @Expose
    public String seller_promo_eligible;

    @SerializedName("seller_store_image")
    @Expose
    public String sellerStoreImage;
    @SerializedName("seller_store_name")
    @Expose
    public String sellerStoreName;
    @SerializedName("seller_store_address")
    @Expose
    public String sellerStoreAddress;
    @SerializedName("seller_city")
    @Expose
    public String sellerCity;
    @SerializedName("seller_pincode")
    @Expose
    public String sellerPincode;
    @SerializedName("seller_rating")
    @Expose
    public String sellerRating;
    @SerializedName("seller_lat")
    @Expose
    public String sellerLat;
    @SerializedName("seller_long")
    @Expose
    public String sellerLong;

    @SerializedName("wish_status")
    @Expose
    public String wish_status;

    @SerializedName("seller_distance")
    @Expose
    public Integer sellerDistance;


    @SerializedName("seller_time")
    @Expose
    public String seller_time;


    @SerializedName("seller_ostatus")
    @Expose
    public String store_o;
//for renewal agent
    @SerializedName("seller_phone")
    @Expose
    public String seller_phone;

    @SerializedName("seller_validity")
    @Expose
    public String seller_validity;

    @SerializedName("t_amount")
    @Expose
    public String t_amount;

    @SerializedName("seller_minimum_order")
    @Expose
    public String seller_minimum_order;

    @SerializedName("seller_offer")
    @Expose
    public String seller_offer ;

    @SerializedName("seller_scratch")
    @Expose
    public String seller_scratch ;

    @SerializedName("coup_stitle")
    @Expose
    public String coup_stitle ;

    public Stores(String sellerId, String seller_promo_status, String seller_dlv_status, String seller_promo_eligible, String sellerStoreImage, String sellerStoreName, String sellerStoreAddress, String sellerCity, String sellerPincode, String sellerRating, String sellerLat, String sellerLong, String wish_status, Integer sellerDistance, String seller_time, String store_o, String seller_phone, String seller_validity, String t_amount, String seller_minimum_order, String seller_offer, String seller_scratch, String coup_stitle) {
        this.sellerId = sellerId;
        this.seller_promo_status = seller_promo_status;
        this.seller_dlv_status = seller_dlv_status;
        this.seller_promo_eligible = seller_promo_eligible;
        this.sellerStoreImage = sellerStoreImage;
        this.sellerStoreName = sellerStoreName;
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerCity = sellerCity;
        this.sellerPincode = sellerPincode;
        this.sellerRating = sellerRating;
        this.sellerLat = sellerLat;
        this.sellerLong = sellerLong;
        this.wish_status = wish_status;
        this.sellerDistance = sellerDistance;
        this.seller_time = seller_time;
        this.store_o = store_o;
        this.seller_phone = seller_phone;
        this.seller_validity = seller_validity;
        this.t_amount = t_amount;
        this.seller_minimum_order = seller_minimum_order;
        this.seller_offer = seller_offer;
        this.seller_scratch = seller_scratch;
        this.coup_stitle = coup_stitle;
    }

    public String getSeller_promo_status() {
        return seller_promo_status;
    }

    public void setSeller_promo_status(String seller_promo_status) {
        this.seller_promo_status = seller_promo_status;
    }

    public String getSeller_dlv_status() {
        return seller_dlv_status;
    }

    public void setSeller_dlv_status(String seller_dlv_status) {
        this.seller_dlv_status = seller_dlv_status;
    }

    public String getSeller_promo_eligible() {
        return seller_promo_eligible;
    }

    public void setSeller_promo_eligible(String seller_promo_eligible) {
        this.seller_promo_eligible = seller_promo_eligible;
    }

    public String getSeller_scratch() {
        return seller_scratch;
    }

    public void setSeller_scratch(String seller_scratch) {
        this.seller_scratch = seller_scratch;
    }

    public String getCoup_stitle() {
        return coup_stitle;
    }

    public void setCoup_stitle(String coup_stitle) {
        this.coup_stitle = coup_stitle;
    }

    public String getSeller_minimum_order() {
        return seller_minimum_order;
    }

    public void setSeller_minimum_order(String seller_minimum_order) {
        this.seller_minimum_order = seller_minimum_order;
    }

    public String getSeller_offer() {
        return seller_offer;
    }

    public void setSeller_offer(String seller_offer) {
        this.seller_offer = seller_offer;
    }

    public String getT_amount() {
        return t_amount;
    }

    public void setT_amount(String t_amount) {
        this.t_amount = t_amount;
    }

    public String getSeller_validity() {
        return seller_validity;
    }

    public void setSeller_validity(String seller_validity) {
        this.seller_validity = seller_validity;
    }

    public String getSeller_time() {
        return seller_time;
    }

    public void setSeller_time(String seller_time) {
        this.seller_time = seller_time;
    }

    public String getSeller_phone() {
        return seller_phone;
    }

    public void setSeller_phone(String seller_phone) {
        this.seller_phone = seller_phone;
    }

    public String getStore_o() {
        return store_o;
    }

    public void setStore_o(String store_o) {
        this.store_o = store_o;
    }

    public String getWish_status() {
        return wish_status;
    }

    public void setWish_status(String wish_status) {
        this.wish_status = wish_status;
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
}
