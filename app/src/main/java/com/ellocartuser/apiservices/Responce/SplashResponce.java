package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.Splash;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplashResponce {



    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("splash")
    @Expose
    public List<Splash> splash;


    public SplashResponce(String status, List<Splash> splash) {
        this.status = status;
        this.splash = splash;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Splash> getSplash() {
        return splash;
    }

    public void setSplash(List<Splash> splash) {
        this.splash = splash;
    }
}
