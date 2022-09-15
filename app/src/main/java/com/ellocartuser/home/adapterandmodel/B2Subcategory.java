package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B2Subcategory {

    @SerializedName("b2subcategory_id")
    @Expose
    public String b2subcategory_id;
    @SerializedName("b2subcategory_name")
    @Expose
    public String b2subcategory_name;
    @SerializedName("b2subcategory_image")
    @Expose
    public String b2subcategory_image;

    public B2Subcategory(String b2subcategory_id, String b2subcategory_name, String b2subcategory_image) {
        this.b2subcategory_id = b2subcategory_id;
        this.b2subcategory_name = b2subcategory_name;
        this.b2subcategory_image = b2subcategory_image;
    }

    public String getB2subcategory_id() {
        return b2subcategory_id;
    }

    public void setB2subcategory_id(String b2subcategory_id) {
        this.b2subcategory_id = b2subcategory_id;
    }

    public String getB2subcategory_name() {
        return b2subcategory_name;
    }

    public void setB2subcategory_name(String b2subcategory_name) {
        this.b2subcategory_name = b2subcategory_name;
    }

    public String getB2subcategory_image() {
        return b2subcategory_image;
    }

    public void setB2subcategory_image(String b2subcategory_image) {
        this.b2subcategory_image = b2subcategory_image;
    }
}
