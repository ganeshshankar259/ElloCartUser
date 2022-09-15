package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.ProductDetailedModal;
import com.ellocartuser.home.adapterandmodel.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailedResponce {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("product")
    @Expose
    public List<ProductDetailedModal> product = null;
    @SerializedName("reviews")
    @Expose
    public List<Review> reviews = null;


    @SerializedName("ratings")
    @Expose
    public List<Review> ratings = null;

    @SerializedName("carted")
    @Expose
    public String carted;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("cart_max")
    @Expose
    public String cart_max;
    @SerializedName("cart_min")
    @Expose
    public String cart_min;
    @SerializedName("seller_ostatus")
    @Expose
    public String store_o;


    public ProductDetailedResponce(String status, List<ProductDetailedModal> product, List<Review> reviews, List<Review> ratings, String carted, String type, String sellerId, String productId, String message, String cart_max, String cart_min, String store_o) {
        this.status = status;
        this.product = product;
        this.reviews = reviews;
        this.ratings = ratings;
        this.carted = carted;
        this.type = type;
        this.sellerId = sellerId;
        this.productId = productId;
        this.message = message;
        this.cart_max = cart_max;
        this.cart_min = cart_min;
        this.store_o = store_o;
    }

    public List<Review> getRatings() {
        return ratings;
    }

    public void setRatings(List<Review> ratings) {
        this.ratings = ratings;
    }

    public String getStore_o() {
        return store_o;
    }

    public void setStore_o(String store_o) {
        this.store_o = store_o;
    }

    public String getCart_max() {
        return cart_max;
    }

    public void setCart_max(String cart_max) {
        this.cart_max = cart_max;
    }

    public String getCart_min() {
        return cart_min;
    }

    public void setCart_min(String cart_min) {
        this.cart_min = cart_min;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetailedModal> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDetailedModal> product) {
        this.product = product;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getCarted() {
        return carted;
    }

    public void setCarted(String carted) {
        this.carted = carted;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}



