package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TAppointListBUid_M {

    @SerializedName("seller_s_address1")
    @Expose
    private String sellerStoreAddress;
    @SerializedName("seller_s_store_name")
    @Expose
    private String sellerStoreName;
    @SerializedName("post_img1")
    @Expose
    private String post_img1;
    @SerializedName("seller_s_city")
    @Expose
    private String sellerCity;
    @SerializedName("seller_s_phone")
    @Expose
    private String sellerPhone;
    @SerializedName("post_title")
    @Expose
    private String post_title;
    @SerializedName("seller_s_id")
    @Expose
    private String sellerId;
    @SerializedName("a_date")
    @Expose
    private String aDate;
    @SerializedName("a_time")
    @Expose
    private String aTime;
    @SerializedName("a_pay_status")
    @Expose
    private String a_pay_status;



    public TAppointListBUid_M(String sellerStoreAddress, String sellerStoreName, String post_img1, String sellerCity, String sellerPhone,String post_title, String sellerId, String aDate, String aTime, String a_pay_status) {
        super();
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerStoreName = sellerStoreName;
        this.post_img1 = post_img1;
        this.sellerCity = sellerCity;
        this.sellerPhone = sellerPhone;
        this.post_title = post_title;
        this.sellerId = sellerId;
        this.aDate = aDate;
        this.aTime = aTime;
        this.a_pay_status = a_pay_status;
    }

    public String getSellerStoreAddress() {
        return sellerStoreAddress;
    }

    public void setSellerStoreAddress(String sellerStoreAddress) {
        this.sellerStoreAddress = sellerStoreAddress;
    }

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }

    public String getSellerStoreImage() {
        return post_img1;
    }

    public void setSellerStoreImage(String sellerStoreImage) {
        this.post_img1 = sellerStoreImage;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getA_pay_status() {
        return a_pay_status;
    }

    public void setA_pay_status(String a_pay_status) {
        this.a_pay_status = a_pay_status;
    }
}
