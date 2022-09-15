package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.B2Subcategory;
import com.ellocartuser.home.adapterandmodel.B2category;
import com.ellocartuser.home.adapterandmodel.Products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class B2categoryResponce {

    @SerializedName("b2categories")
    @Expose
    public List<B2category> b2categories = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("b2subcategories")
    @Expose
    public List<B2Subcategory> b2subcategories = null;
    @SerializedName("product")
    @Expose
    public List<Products> product = null;

    public B2categoryResponce(List<B2category> b2categories, String status, String type, List<B2Subcategory> b2subcategories, List<Products> product) {
        this.b2categories = b2categories;
        this.status = status;
        this.type = type;
        this.b2subcategories = b2subcategories;
        this.product = product;
    }

    public List<Products> getProduct() {
        return product;
    }

    public void setProduct(List<Products> product) {
        this.product = product;
    }

    public List<B2Subcategory> getB2subcategories() {
        return b2subcategories;
    }

    public void setB2subcategories(List<B2Subcategory> b2subcategories) {
        this.b2subcategories = b2subcategories;
    }
    public List<B2category> getB2categories() {
        return b2categories;
    }

    public void setB2categories(List<B2category> b2categories) {
        this.b2categories = b2categories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
