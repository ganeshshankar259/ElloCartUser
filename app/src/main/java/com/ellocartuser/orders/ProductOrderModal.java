package com.ellocartuser.orders;

import com.google.gson.annotations.SerializedName;

public class ProductOrderModal {

    @SerializedName("ordered_id")
    public String orderedId;
    @SerializedName("product_id")
    public String productId;
    @SerializedName("product_name")
    public String productName;
    @SerializedName("product_measure")
    public String productMeasure;
    @SerializedName("product_img1")
    public String productImg1;
    @SerializedName("product_review")
    public String productReview;
    @SerializedName("ordered_amount")
    public String orderedAmount;
    @SerializedName("ordered_qty")
    public String orderedQty;

    public ProductOrderModal(String orderedId, String productId, String productName, String productMeasure, String productImg1, String productReview, String orderedAmount, String orderedQty) {
        this.orderedId = orderedId;
        this.productId = productId;
        this.productName = productName;
        this.productMeasure = productMeasure;
        this.productImg1 = productImg1;
        this.productReview = productReview;
        this.orderedAmount = orderedAmount;
        this.orderedQty = orderedQty;
    }

    public String getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(String orderedId) {
        this.orderedId = orderedId;
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

    public String getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getProductImg1() {
        return productImg1;
    }

    public void setProductImg1(String productImg1) {
        this.productImg1 = productImg1;
    }

    public String getProductReview() {
        return productReview;
    }

    public void setProductReview(String productReview) {
        this.productReview = productReview;
    }

    public String getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(String orderedAmount) {
        this.orderedAmount = orderedAmount;
    }

    public String getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(String orderedQty) {
        this.orderedQty = orderedQty;
    }
}
