package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryStoreAll {


    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("category_image3")
    @Expose
    private String category_image;

    @SerializedName("selected")
    @Expose
    private String selected;

    public CategoryStoreAll(String category_id, String category_name, String category_image, String selected) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_image = category_image;
        this.selected = selected;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
