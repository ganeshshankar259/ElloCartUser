package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class Notify {

    @SerializedName("notify_id")
    public String notifyId;
    @SerializedName("notify_title")
    public String notifyTitle;
    @SerializedName("notify_message")
    public String notifyMessage;
    @SerializedName("notify_type")
    public String notifyType;
    @SerializedName("notify_p1")
    public String notifyP1;
    @SerializedName("notify_p2")
    public String notifyP2;
    @SerializedName("notify_p3")
    public String notifyP3;

    public Notify(String notifyId, String notifyTitle, String notifyMessage, String notifyType, String notifyP1, String notifyP2, String notifyP3) {
        this.notifyId = notifyId;
        this.notifyTitle = notifyTitle;
        this.notifyMessage = notifyMessage;
        this.notifyType = notifyType;
        this.notifyP1 = notifyP1;
        this.notifyP2 = notifyP2;
        this.notifyP3 = notifyP3;
    }

    public String getNotifyP1() {
        return notifyP1;
    }

    public void setNotifyP1(String notifyP1) {
        this.notifyP1 = notifyP1;
    }

    public String getNotifyP2() {
        return notifyP2;
    }

    public void setNotifyP2(String notifyP2) {
        this.notifyP2 = notifyP2;
    }

    public String getNotifyP3() {
        return notifyP3;
    }

    public void setNotifyP3(String notifyP3) {
        this.notifyP3 = notifyP3;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifyMessage() {
        return notifyMessage;
    }

    public void setNotifyMessage(String notifyMessage) {
        this.notifyMessage = notifyMessage;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }
}
