package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponce {

    @SerializedName("user_id")
    @Expose
    public String userId;

    @SerializedName("cart_count")
    @Expose
    public Integer cartCount;

    @SerializedName("items_total")
    @Expose
    public Integer items_total;

    @SerializedName("cart_save")
    @Expose
    public Integer cart_save;

    @SerializedName("cart_total")
    @Expose
    public Integer cartTotal;

    @SerializedName("cart_cod")
    @Expose
    public String cart_cod;

    @SerializedName("cart")
    @Expose
    public List<Cart> cart = null;

    @SerializedName("seller_id")
    @Expose
    public String sellerId;

    @SerializedName("seller_store_name")
    @Expose
    public String sellerStoreName;

    @SerializedName("seller_phone")
    @Expose
    public String sellerPhone;

    @SerializedName("seller_store_image")
    @Expose
    public String sellerStoreImage;

    @SerializedName("seller_store_address")
    @Expose
    public String sellerStoreAddress;

    @SerializedName("seller_city")
    @Expose
    public String sellerCity;

    @SerializedName("seller_pincode")
    @Expose
    public String sellerPincode;

    @SerializedName("seller_lat")
    @Expose
    public String sellerLat;

    @SerializedName("seller_long")
    @Expose
    public String sellerLong;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("seller_minimum_order")
    @Expose
    public String seller_minimum_order;

    @SerializedName("delivery_charges")
    @Expose
    public String delivery_charges;


    @SerializedName("seller_time")
    @Expose
    public String seller_time;

    @SerializedName("seller_day")
    @Expose
    public String seller_day;

    @SerializedName("seller_type")
    @Expose
    public String seller_type;

    @SerializedName("seller_ostatus")
    @Expose
    public String seller_ostatus;

    public CartResponce(String userId, Integer cartCount, Integer items_total, Integer cart_save, Integer cartTotal, String cart_cod, List<Cart> cart, String sellerId, String sellerStoreName, String sellerPhone, String sellerStoreImage, String sellerStoreAddress, String sellerCity, String sellerPincode, String sellerLat, String sellerLong, String status, String message, String type, String seller_minimum_order, String delivery_charges, String seller_time, String seller_day, String seller_type, String seller_ostatus) {
        this.userId = userId;
        this.cartCount = cartCount;
        this.items_total = items_total;
        this.cart_save = cart_save;
        this.cartTotal = cartTotal;
        this.cart_cod = cart_cod;
        this.cart = cart;
        this.sellerId = sellerId;
        this.sellerStoreName = sellerStoreName;
        this.sellerPhone = sellerPhone;
        this.sellerStoreImage = sellerStoreImage;
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerCity = sellerCity;
        this.sellerPincode = sellerPincode;
        this.sellerLat = sellerLat;
        this.sellerLong = sellerLong;
        this.status = status;
        this.message = message;
        this.type = type;
        this.seller_minimum_order = seller_minimum_order;
        this.delivery_charges = delivery_charges;
        this.seller_time = seller_time;
        this.seller_day = seller_day;
        this.seller_type = seller_type;
        this.seller_ostatus = seller_ostatus;
    }

    public String getSeller_day() {
        return seller_day;
    }

    public void setSeller_day(String seller_day) {
        this.seller_day = seller_day;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getSeller_ostatus() {
        return seller_ostatus;
    }

    public void setSeller_ostatus(String seller_ostatus) {
        this.seller_ostatus = seller_ostatus;
    }

    public Integer getCart_save() {
        return cart_save;
    }

    public void setCart_save(Integer cart_save) {
        this.cart_save = cart_save;
    }

    public Integer getItems_total() {
        return items_total;
    }

    public void setItems_total(Integer items_total) {
        this.items_total = items_total;
    }

    public String getSeller_time() {
        return seller_time;
    }

    public void setSeller_time(String seller_time) {
        this.seller_time = seller_time;
    }

    public String getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(String delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getCart_cod() {
        return cart_cod;
    }

    public void setCart_cod(String cart_cod) {
        this.cart_cod = cart_cod;
    }

    public String getSeller_minimum_order() {
        return seller_minimum_order;
    }

    public void setSeller_minimum_order(String seller_minimum_order) {
        this.seller_minimum_order = seller_minimum_order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Integer getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Integer cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
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

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerStoreImage() {
        return sellerStoreImage;
    }

    public void setSellerStoreImage(String sellerStoreImage) {
        this.sellerStoreImage = sellerStoreImage;
    }

    public String getSellerStoreAddress() {
        return sellerStoreAddress;
    }

    public void setSellerStoreAddress(String sellerStoreAddress) {
        this.sellerStoreAddress = sellerStoreAddress;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerPincode() {
        return sellerPincode;
    }

    public void setSellerPincode(String sellerPincode) {
        this.sellerPincode = sellerPincode;
    }

    public String getSellerLat() {
        return sellerLat;
    }

    public void setSellerLat(String sellerLat) {
        this.sellerLat = sellerLat;
    }

    public String getSellerLong() {
        return sellerLong;
    }

    public void setSellerLong(String sellerLong) {
        this.sellerLong = sellerLong;
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
}
