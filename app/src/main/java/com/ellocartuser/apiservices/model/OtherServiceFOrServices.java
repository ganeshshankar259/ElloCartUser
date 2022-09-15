package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class OtherServiceFOrServices {

    @SerializedName("servcat_id")
    public String servcatId;
    @SerializedName("servcat_name")
    public String servcatName;
    @SerializedName("servcat_image")
    public String servcatImage;


    public OtherServiceFOrServices(String servcatId, String servcatName, String servcatImage) {
        this.servcatId = servcatId;
        this.servcatName = servcatName;
        this.servcatImage = servcatImage;
    }

    public String getServcatId() {
        return servcatId;
    }

    public void setServcatId(String servcatId) {
        this.servcatId = servcatId;
    }

    public String getServcatName() {
        return servcatName;
    }

    public void setServcatName(String servcatName) {
        this.servcatName = servcatName;
    }

    public String getServcatImage() {
        return servcatImage;
    }

    public void setServcatImage(String servcatImage) {
        this.servcatImage = servcatImage;
    }
}
