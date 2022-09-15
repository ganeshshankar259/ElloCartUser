package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B2category {


    @SerializedName("b2category_id")
    @Expose
    public String b2categoryId;
    @SerializedName("b2category_name")
    @Expose
    public String b2categoryName;
    @SerializedName("b2category_image2")
    @Expose
    public String b2categoryImage;

    public B2category(String b2categoryId, String b2categoryName, String b2categoryImage) {
        this.b2categoryId = b2categoryId;
        this.b2categoryName = b2categoryName;
        this.b2categoryImage = b2categoryImage;
    }

    public String getB2categoryId() {
        return b2categoryId;
    }

    public void setB2categoryId(String b2categoryId) {
        this.b2categoryId = b2categoryId;
    }

    public String getB2categoryName() {
        return b2categoryName;
    }

    public void setB2categoryName(String b2categoryName) {
        this.b2categoryName = b2categoryName;
    }

    public String getB2categoryImage() {
        return b2categoryImage;
    }

    public void setB2categoryImage(String b2categoryImage) {
        this.b2categoryImage = b2categoryImage;
    }
}
