package com.ellocartuser.apiservices.Responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOutPaymentResponce {

    @SerializedName("order_type2")
    public String order_type2;
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("user_id")
    public String userId;
    @SerializedName("order_id")
    public String orderId;
    @SerializedName("order_date")
    public String orderDate;
    @SerializedName("order_time")
    public String orderTime;
    @SerializedName("order_pay_type")
    public String orderPayType;
    @SerializedName("order_pay_status") //if 1 ietee direct pay
    public String orderPayStatus;
    @SerializedName("order_payid")
    public String orderPayid;
    @SerializedName("order_final")
    public String orderFinal;
    @SerializedName("order_wallet")
    public String order_wallet;
    @SerializedName("message")
    public String message;
    @SerializedName("message2")
    public String message2;

    public CheckOutPaymentResponce(String order_type2, String status, String type, String userId, String orderId, String orderDate, String orderTime, String orderPayType, String orderPayStatus, String orderPayid, String orderFinal, String message, String message2) {
        this.order_type2 = order_type2;
        this.status = status;
        this.type = type;
        this.userId = userId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderPayType = orderPayType;
        this.orderPayStatus = orderPayStatus;
        this.orderPayid = orderPayid;
        this.orderFinal = orderFinal;
        this.message = message;
        this.message2 = message2;
    }

    public String getOrder_type2() {
        return order_type2;
    }

    public void setOrder_type2(String order_type2) {
        this.order_type2 = order_type2;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(String orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public String getOrderPayid() {
        return orderPayid;
    }

    public void setOrderPayid(String orderPayid) {
        this.orderPayid = orderPayid;
    }

    public String getOrderFinal() {
        return orderFinal;
    }

    public void setOrderFinal(String orderFinal) {
        this.orderFinal = orderFinal;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
