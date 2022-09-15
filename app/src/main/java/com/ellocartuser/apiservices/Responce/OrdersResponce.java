package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.orders.OrderModel;
import com.ellocartuser.orders.ProductOrderModal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrdersResponce {

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("orders")
    @Expose
    public List<OrderModel> orders = null;

    @SerializedName("order")
    @Expose
    public List<OrderModel> order = null;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("message2")
    @Expose
    public String message2;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("user_id")
    @Expose
    public String userId;

    @SerializedName("product_review")
    @Expose
    public String product_review;

    @SerializedName("products")
    @Expose
    public List<ProductOrderModal> products;

    @SerializedName("address")
    @Expose
    public List<com.ellocartuser.home.adapterandmodel.Address> address;

    public OrdersResponce(String status, List<OrderModel> orders, List<OrderModel> order, String message, String message2, String type, String userId, String product_review, List<ProductOrderModal> products, List<Address> address) {
        this.status = status;
        this.orders = orders;
        this.order = order;
        this.message = message;
        this.message2 = message2;
        this.type = type;
        this.userId = userId;
        this.product_review = product_review;
        this.products = products;
        this.address = address;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getProduct_review() {
        return product_review;
    }

    public void setProduct_review(String product_review) {
        this.product_review = product_review;
    }

    public List<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(List<OrderModel> order) {
        this.order = order;
    }

    public List<ProductOrderModal> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOrderModal> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
