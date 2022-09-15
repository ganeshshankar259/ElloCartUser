package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {

    @SerializedName("cart_id")
    @Expose
    public String cartId;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("seller_day")
    @Expose
    public String seller_day;
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
    @SerializedName("product_sale")
    @Expose
    public String productSale;
    @SerializedName("product_img1")
    @Expose
    public String productImg1;
    @SerializedName("cart_qty")
    @Expose
    public String cartQty;
    @SerializedName("cart_price_mrp")
    @Expose
    public Integer cartPrice;
    @SerializedName("product_mrp")
    @Expose
    public String productMrp;


    public Cart(String cartId, String sellerId, String seller_day, String productId, String sproductId, String productName, String productMeasure, String productSale, String productImg1, String cartQty, Integer cartPrice, String productMrp) {
        this.cartId = cartId;
        this.sellerId = sellerId;
        this.seller_day = seller_day;
        this.productId = productId;
        this.sproductId = sproductId;
        this.productName = productName;
        this.productMeasure = productMeasure;
        this.productSale = productSale;
        this.productImg1 = productImg1;
        this.cartQty = cartQty;
        this.cartPrice = cartPrice;
        this.productMrp = productMrp;
    }

    public String getSeller_day() {
        return seller_day;
    }

    public void setSeller_day(String seller_day) {
        this.seller_day = seller_day;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public String getProductSale() {
        return productSale;
    }

    public void setProductSale(String productSale) {
        this.productSale = productSale;
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

    public Integer getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Integer cartPrice) {
        this.cartPrice = cartPrice;
    }
}
