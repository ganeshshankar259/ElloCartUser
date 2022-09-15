package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubcategoryStoreAll {

    @SerializedName("subcategory_id")
    public String subcategoryId;
    @SerializedName("subcategory_name")
    public String subcategoryName;
    @SerializedName("subcategory_image")
    public String subcategoryImage;
    @SerializedName("selected")
    @Expose
    private String selected;
    // for services

    @SerializedName("servsubcategory_id")
    public String scat_id;
    @SerializedName("servsubcategory_name")
    public String scat_name;
    @SerializedName("servsubcategory_image")
    public String scat_image;


    public SubcategoryStoreAll(String subcategoryId, String subcategoryName, String subcategoryImage, String selected, String scat_id, String scat_name, String scat_image) {
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
        this.subcategoryImage = subcategoryImage;
        this.selected = selected;
        this.scat_id = scat_id;
        this.scat_name = scat_name;
        this.scat_image = scat_image;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getScat_id() {
        return scat_id;
    }

    public void setScat_id(String scat_id) {
        this.scat_id = scat_id;
    }

    public String getScat_name() {
        return scat_name;
    }

    public void setScat_name(String scat_name) {
        this.scat_name = scat_name;
    }

    public String getScat_image() {
        return scat_image;
    }

    public void setScat_image(String scat_image) {
        this.scat_image = scat_image;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getSubcategoryImage() {
        return subcategoryImage;
    }

    public void setSubcategoryImage(String subcategoryImage) {
        this.subcategoryImage = subcategoryImage;
    }
}
