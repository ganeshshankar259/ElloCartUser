package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Products;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponce {

    @SerializedName("product")
    @Expose
    public List<Products> product = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("seller_ostatus")
    @Expose
    public String seller_ostatus;

    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("subcategory_id")
    @Expose
    public String subcategoryId;
    @SerializedName("subcategory_name")
    @Expose
    public String subcategoryName;
    @SerializedName("seller_name")
    @Expose
    public String sellerName;

    public ProductsResponce(List<Products> product, String status, String message, String type, String sellerId, String seller_ostatus, String categoryId, String subcategoryId, String subcategoryName, String sellerName) {
        this.product = product;
        this.status = status;
        this.message = message;
        this.type = type;
        this.sellerId = sellerId;
        this.seller_ostatus = seller_ostatus;
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
        this.sellerName = sellerName;
    }

    public String getSeller_ostatus() {
        return seller_ostatus;
    }

    public void setSeller_ostatus(String seller_ostatus) {
        this.seller_ostatus = seller_ostatus;
    }

    public List<Products> getProduct() {
        return product;
    }

    public void setProduct(List<Products> product) {
        this.product = product;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
