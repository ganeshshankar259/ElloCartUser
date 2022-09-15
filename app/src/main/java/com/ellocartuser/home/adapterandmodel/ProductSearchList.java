package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSearchList {


    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("product_name")
    @Expose
    public String productName;

    @SerializedName("product_mrp")
    @Expose
    public String product_price;

    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("seller_store_name")
    @Expose
    public String sellerStoreName;

    public ProductSearchList(String productId, String productName, String product_price, String sellerId, String categoryId, String sellerStoreName) {
        this.productId = productId;
        this.productName = productName;
        this.product_price = product_price;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.sellerStoreName = sellerStoreName;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }
}


