package com.ellocartuser.apiservices.Responce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponce {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("user_phone")
    @Expose
    private String boy_phone;


    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_email")
    @Expose
    private String user_email;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("user_profile")
    @Expose
    private String user_profile;

    @SerializedName("notify_count")
    @Expose
    private String notify_count;


    public RegisterResponce(String status, String message, String boy_phone, String user_id, String user_email, String user_name, String user_profile, String notify_count) {
        this.status = status;
        this.message = message;
        this.boy_phone = boy_phone;
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_profile = user_profile;
        this.notify_count = notify_count;
    }

    public String getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }

    public String getNotify_count() {
        return notify_count;
    }

    public void setNotify_count(String notify_count) {
        this.notify_count = notify_count;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getBoy_phone() {
        return boy_phone;
    }

    public void setBoy_phone(String boy_phone) {
        this.boy_phone = boy_phone;
    }
}
