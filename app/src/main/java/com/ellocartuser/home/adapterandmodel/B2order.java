package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B2order {

    @SerializedName("b2order_id")
    @Expose
    public String b2orderId;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("seller_store_name")
    @Expose
    public String sellerStoreName;
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("sproduct_id")
    @Expose
    public String sproductId;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("product_measure")
    @Expose
    public String productMeasure;
    @SerializedName("product_img1")
    @Expose
    public String productImg1;
    @SerializedName("cart_qty")
    @Expose
    public String cartQty;


    public B2order(String b2orderId, String sellerId, String sellerStoreName, String productId, String sproductId, String productName, String productMeasure, String productImg1, String cartQty) {
        this.b2orderId = b2orderId;
        this.sellerId = sellerId;
        this.sellerStoreName = sellerStoreName;
        this.productId = productId;
        this.sproductId = sproductId;
        this.productName = productName;
        this.productMeasure = productMeasure;
        this.productImg1 = productImg1;
        this.cartQty = cartQty;
    }

    public String getB2orderId() {
        return b2orderId;
    }

    public void setB2orderId(String b2orderId) {
        this.b2orderId = b2orderId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerStoreName() {
        return sellerStoreName;
    }

    public void setSellerStoreName(String sellerStoreName) {
        this.sellerStoreName = sellerStoreName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSproductId() {
        return sproductId;
    }

    public void setSproductId(String sproductId) {
        this.sproductId = sproductId;
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

    public String getCartQty() {
        return cartQty;
    }

    public void setCartQty(String cartQty) {
        this.cartQty = cartQty;
    }
}
