package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Splash {


    @SerializedName("spl_id")
    @Expose
    public String splId;
    @SerializedName("spl_name")
    @Expose
    public String splName;
    @SerializedName("spl_image")
    @Expose
    public String splImage;

    public Splash(String splId, String splName, String splImage) {
        this.splId = splId;
        this.splName = splName;
        this.splImage = splImage;
    }

    public String getSplId() {
        return splId;
    }

    public void setSplId(String splId) {
        this.splId = splId;
    }

    public String getSplName() {
        return splName;
    }

    public void setSplName(String splName) {
        this.splName = splName;
    }

    public String getSplImage() {
        return splImage;
    }

    public void setSplImage(String splImage) {
        this.splImage = splImage;
    }
}
