package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TAppoint_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("appoint_id")
    @Expose
    private Integer appointId;
    @SerializedName("order_id")
    @Expose
    private String orderId;

    public TAppoint_Responce(String status, String msg, Integer appointId, String orderId) {
        super();
        this.status = status;
        this.msg = msg;
        this.appointId = appointId;
        this.orderId = orderId;
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

    public Integer getAppointId() {
        return appointId;
    }

    public void setAppointId(Integer appointId) {
        this.appointId = appointId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
