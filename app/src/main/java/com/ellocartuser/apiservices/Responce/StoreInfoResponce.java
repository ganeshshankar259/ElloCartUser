package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.SellerBanners;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreInfoResponce {


    @SerializedName("status")
    public String status;
    @SerializedName("msg")
    public String msg;
    @SerializedName("seller_id")
    public String sellerId;
    @SerializedName("seller_store_name")
    public String sellerStoreName;
    @SerializedName("seller_email")
    public String sellerEmail;
    @SerializedName("seller_phone")
    public String sellerPhone;
    @SerializedName("seller_store_address")
    public String sellerStoreAddress;
    @SerializedName("seller_country")
    public String sellerCountry;
    @SerializedName("seller_state")
    public String sellerState;
    @SerializedName("seller_city")
    public String sellerCity;
    @SerializedName("seller_pincode")
    public String sellerPincode;
    @SerializedName("seller_store_image")
    public String sellerStoreImage;
    @SerializedName("seller_minimum_order")
    public String sellerMinimumOrder;
    @SerializedName("seller_time")
    public String sellerTime;
    @SerializedName("seller_lat")
    public String sellerLat;
    @SerializedName("seller_long")
    public String sellerLong;
    @SerializedName("rating_avg")
    public Integer ratingAvg;
    @SerializedName("rating_total")
    public Integer ratingTotal;
    @SerializedName("rating_total_1")
    public Integer ratingTotal1;
    @SerializedName("rating_total_2")
    public Integer ratingTotal2;
    @SerializedName("rating_total_3")
    public Integer ratingTotal3;
    @SerializedName("rating_total_4")
    public Integer ratingTotal4;
    @SerializedName("rating_total_5")
    public Integer ratingTotal5;
    @SerializedName("seller_banners")
    public List<SellerBanners> seller_banners;


    public StoreInfoResponce(String status, String msg, String sellerId, String sellerStoreName, String sellerEmail, String sellerPhone, String sellerStoreAddress, String sellerCountry, String sellerState, String sellerCity, String sellerPincode, String sellerStoreImage, String sellerMinimumOrder, String sellerTime, String sellerLat, String sellerLong, Integer ratingAvg, Integer ratingTotal, Integer ratingTotal1, Integer ratingTotal2, Integer ratingTotal3, Integer ratingTotal4, Integer ratingTotal5, List<SellerBanners> seller_banners) {
        this.status = status;
        this.msg = msg;
        this.sellerId = sellerId;
        this.sellerStoreName = sellerStoreName;
        this.sellerEmail = sellerEmail;
        this.sellerPhone = sellerPhone;
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerCountry = sellerCountry;
        this.sellerState = sellerState;
        this.sellerCity = sellerCity;
        this.sellerPincode = sellerPincode;
        this.sellerStoreImage = sellerStoreImage;
        this.sellerMinimumOrder = sellerMinimumOrder;
        this.sellerTime = sellerTime;
        this.sellerLat = sellerLat;
        this.sellerLong = sellerLong;
        this.ratingAvg = ratingAvg;
        this.ratingTotal = ratingTotal;
        this.ratingTotal1 = ratingTotal1;
        this.ratingTotal2 = ratingTotal2;
        this.ratingTotal3 = ratingTotal3;
        this.ratingTotal4 = ratingTotal4;
        this.ratingTotal5 = ratingTotal5;
        this.seller_banners = seller_banners;
    }

    public List<SellerBanners> getSeller_banners() {
        return seller_banners;
    }

    public void setSeller_banners(List<SellerBanners> seller_banners) {
        this.seller_banners = seller_banners;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerStoreAddress() {
        return sellerStoreAddress;
    }

    public void setSellerStoreAddress(String sellerStoreAddress) {
        this.sellerStoreAddress = sellerStoreAddress;
    }

    public String getSellerCountry() {
        return sellerCountry;
    }

    public void setSellerCountry(String sellerCountry) {
        this.sellerCountry = sellerCountry;
    }

    public String getSellerState() {
        return sellerState;
    }

    public void setSellerState(String sellerState) {
        this.sellerState = sellerState;
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

    public String getSellerStoreImage() {
        return sellerStoreImage;
    }

    public void setSellerStoreImage(String sellerStoreImage) {
        this.sellerStoreImage = sellerStoreImage;
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

    public Integer getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Integer ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public Integer getRatingTotal() {
        return ratingTotal;
    }

    public void setRatingTotal(Integer ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    public Integer getRatingTotal1() {
        return ratingTotal1;
    }

    public void setRatingTotal1(Integer ratingTotal1) {
        this.ratingTotal1 = ratingTotal1;
    }

    public Integer getRatingTotal2() {
        return ratingTotal2;
    }

    public void setRatingTotal2(Integer ratingTotal2) {
        this.ratingTotal2 = ratingTotal2;
    }

    public Integer getRatingTotal3() {
        return ratingTotal3;
    }

    public void setRatingTotal3(Integer ratingTotal3) {
        this.ratingTotal3 = ratingTotal3;
    }

    public Integer getRatingTotal4() {
        return ratingTotal4;
    }

    public void setRatingTotal4(Integer ratingTotal4) {
        this.ratingTotal4 = ratingTotal4;
    }

    public Integer getRatingTotal5() {
        return ratingTotal5;
    }

    public void setRatingTotal5(Integer ratingTotal5) {
        this.ratingTotal5 = ratingTotal5;
    }
}
