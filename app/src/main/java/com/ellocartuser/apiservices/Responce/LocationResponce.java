package com.ellocartuser.apiservices.Responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponce {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("boy_name")
    @Expose
    public String boyName;
    @SerializedName("boy_phone_code")
    @Expose
    public String boyPhoneCode;
    @SerializedName("boy_image")
    @Expose
    public String boy_image;
//    @SerializedName("boy_lat")
//    @Expose
//    public String boyLat;
//    @SerializedName("boy_long")
//    @Expose
//    public String boyLong;
    @SerializedName("addr_lat")
    @Expose
    public String addrLat;
    @SerializedName("addr_long")
    @Expose
    public String addrLong;
    @SerializedName("seller_lat")
    @Expose
    public String seller_lat;
    @SerializedName("seller_long")
    @Expose
    public String seller_long;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("order_status")
    @Expose
    public String order_status;

    @SerializedName("order_assign")
    @Expose
    public String order_assign;

    @SerializedName("boy_long")
    @Expose
    public String boy_long;

    @SerializedName("boy_lat")
    @Expose
    public String boy_lat;
    @SerializedName("delivery_time")
    @Expose
    public String delivery_time;
    @SerializedName("boy_phone")
    @Expose
    public String boy_phone;
    @SerializedName("support_phone")
    @Expose
    public String support_phone;
//    @SerializedName("boy_name")
//    @Expose
//    public String boy_name;

    @SerializedName("order_id")
    @Expose
    public String orderId;

    public LocationResponce(String status, String boyName, String boyPhoneCode, String boy_image, String addrLat, String addrLong, String seller_lat, String seller_long, String userId, String order_status, String order_assign, String boy_long, String boy_lat, String delivery_time, String boy_phone, String support_phone, String orderId) {
        this.status = status;
        this.boyName = boyName;
        this.boyPhoneCode = boyPhoneCode;
        this.boy_image = boy_image;
        this.addrLat = addrLat;
        this.addrLong = addrLong;
        this.seller_lat = seller_lat;
        this.seller_long = seller_long;
        this.userId = userId;
        this.order_status = order_status;
        this.order_assign = order_assign;
        this.boy_long = boy_long;
        this.boy_lat = boy_lat;
        this.delivery_time = delivery_time;
        this.boy_phone = boy_phone;
        this.support_phone = support_phone;
        this.orderId = orderId;
    }

    public String getSupport_phone() {
        return support_phone;
    }

    public void setSupport_phone(String support_phone) {
        this.support_phone = support_phone;
    }

    public String getBoy_image() {
        return boy_image;
    }

    public void setBoy_image(String boy_image) {
        this.boy_image = boy_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public String getBoyPhoneCode() {
        return boyPhoneCode;
    }

    public void setBoyPhoneCode(String boyPhoneCode) {
        this.boyPhoneCode = boyPhoneCode;
    }

    public String getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(String addrLat) {
        this.addrLat = addrLat;
    }

    public String getAddrLong() {
        return addrLong;
    }

    public void setAddrLong(String addrLong) {
        this.addrLong = addrLong;
    }

    public String getSeller_lat() {
        return seller_lat;
    }

    public void setSeller_lat(String seller_lat) {
        this.seller_lat = seller_lat;
    }

    public String getSeller_long() {
        return seller_long;
    }

    public void setSeller_long(String seller_long) {
        this.seller_long = seller_long;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_assign() {
        return order_assign;
    }

    public void setOrder_assign(String order_assign) {
        this.order_assign = order_assign;
    }

    public String getBoy_long() {
        return boy_long;
    }

    public void setBoy_long(String boy_long) {
        this.boy_long = boy_long;
    }

    public String getBoy_lat() {
        return boy_lat;
    }

    public void setBoy_lat(String boy_lat) {
        this.boy_lat = boy_lat;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getBoy_phone() {
        return boy_phone;
    }

    public void setBoy_phone(String boy_phone) {
        this.boy_phone = boy_phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
