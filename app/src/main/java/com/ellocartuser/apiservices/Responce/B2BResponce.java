package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.Notify;
import com.ellocartuser.home.adapterandmodel.B2order;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class B2BResponce {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("b2orders")
    @Expose
    public List<B2order> b2orders = null;


    @SerializedName("notifys")
    public List<Notify> notifys = null;

    public B2BResponce(String status, String message, List<B2order> b2orders, List<Notify> notifys) {
        this.status = status;
        this.message = message;
        this.b2orders = b2orders;
        this.notifys = notifys;
    }

    public List<Notify> getNotifys() {
        return notifys;
    }

    public void setNotifys(List<Notify> notifys) {
        this.notifys = notifys;
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

    public List<B2order> getB2orders() {
        return b2orders;
    }

    public void setB2orders(List<B2order> b2orders) {
        this.b2orders = b2orders;
    }
}
