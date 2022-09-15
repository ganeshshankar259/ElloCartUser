package com.ellocartuser.ellorooms_new.models;

import com.google.gson.annotations.SerializedName;

public class Amenities {

    @SerializedName("a_id")
    private String aId;
    @SerializedName("a_title")
    private String aTitle;
    @SerializedName("a_image")
    private String aImage;


    private int select =0;

    public Amenities(String aId, String aTitle, String aImage, int select) {
        this.aId = aId;
        this.aTitle = aTitle;
        this.aImage = aImage;
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getaImage() {
        return aImage;
    }

    public void setaImage(String aImage) {
        this.aImage = aImage;
    }
}
