package com.ellocartuser.orders;

import com.ellocartuser.apiservices.model.OtherCharge;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel {


    @SerializedName("other_charges")
    @Expose
    public List<OtherCharge> other_charges;

    @SerializedName("order_id")
    @Expose
    public String orderId;
    @SerializedName("seller_id")
    @Expose
    public String sellerId;
    @SerializedName("boy_name")
    @Expose
    public String boy_name;
    @SerializedName("boy_image")
    @Expose
    public String boy_image;

    @SerializedName("seller_store_name")
    @Expose
    public String sellerStoreName;
    @SerializedName("seller_store_image")
    @Expose
    public String sellerStoreImage;
    @SerializedName("seller_phone")
    @Expose
    public String sellerPhone;
    @SerializedName("support_phone")
    @Expose
    public String support_phone;
    @SerializedName("seller_store_address")
    @Expose
    public String sellerStoreAddress;
    @SerializedName("seller_city")
    @Expose
    public String sellerCity;
    @SerializedName("seller_pincode")
    @Expose
    public String sellerPincode;
    @SerializedName("seller_review")
    @Expose
    public String sellerReview;
    @SerializedName("order_date")
    @Expose
    public String orderDate;
    @SerializedName("order_pick_date")
    @Expose
    public String orderPickDate;
    @SerializedName("order_pick_time")
    @Expose
    public String orderPickTime;
    @SerializedName("order_total_items")
    @Expose
    public Integer orderTotalItems;
    @SerializedName("order_total")
    @Expose
    public String orderTotal;
    @SerializedName("order_final")
    @Expose
    public String orderFinal;
    @SerializedName("order_tax")
    @Expose
    public String orderTax;
    @SerializedName("order_delivery")
    @Expose
    public String orderDelivery;
    @SerializedName("order_type")
    @Expose
    public String orderType;
    @SerializedName("order_pay_type")
    @Expose
    public String orderPayType;
    @SerializedName("order_assign")
    @Expose
    public String orderAssign;
    @SerializedName("order_status")
    @Expose
    public String orderStatus;

   @SerializedName("order_coupon")
   // @SerializedName("order_saved")
    @Expose
    public String order_coupon;

    @SerializedName("product_measure")
    @Expose
    public String productMeasure;

    @SerializedName("product_review")
    @Expose
    public String product_review;

    @SerializedName("delivery_review")
    @Expose
    public String delivery_review;

    @SerializedName("order_time")
    @Expose
    public String order_time;

    @SerializedName("order_delv_date")
    @Expose
    public String del_order_date;

    @SerializedName("order_delv_tme")
    @Expose
    public String del_order_time;

    @SerializedName("order_type2")
    @Expose
    public String order_type2;

    @SerializedName("order_track")
    @Expose
    public String order_track;

    public OrderModel(List<OtherCharge> other_charges, String orderId, String sellerId, String boy_name, String boy_image, String sellerStoreName, String sellerStoreImage, String sellerPhone, String support_phone, String sellerStoreAddress, String sellerCity, String sellerPincode, String sellerReview, String orderDate, String orderPickDate, String orderPickTime, Integer orderTotalItems, String orderTotal, String orderFinal, String orderTax, String orderDelivery, String orderType, String orderPayType, String orderAssign, String orderStatus, String order_coupon, String productMeasure, String product_review, String delivery_review, String order_time, String del_order_date, String del_order_time, String order_type2, String order_track) {
        this.other_charges = other_charges;
        this.orderId = orderId;
        this.sellerId = sellerId;
        this.boy_name = boy_name;
        this.boy_image = boy_image;
        this.sellerStoreName = sellerStoreName;
        this.sellerStoreImage = sellerStoreImage;
        this.sellerPhone = sellerPhone;
        this.support_phone = support_phone;
        this.sellerStoreAddress = sellerStoreAddress;
        this.sellerCity = sellerCity;
        this.sellerPincode = sellerPincode;
        this.sellerReview = sellerReview;
        this.orderDate = orderDate;
        this.orderPickDate = orderPickDate;
        this.orderPickTime = orderPickTime;
        this.orderTotalItems = orderTotalItems;
        this.orderTotal = orderTotal;
        this.orderFinal = orderFinal;
        this.orderTax = orderTax;
        this.orderDelivery = orderDelivery;
        this.orderType = orderType;
        this.orderPayType = orderPayType;
        this.orderAssign = orderAssign;
        this.orderStatus = orderStatus;
        this.order_coupon = order_coupon;
        this.productMeasure = productMeasure;
        this.product_review = product_review;
        this.delivery_review = delivery_review;
        this.order_time = order_time;
        this.del_order_date = del_order_date;
        this.del_order_time = del_order_time;
        this.order_type2 = order_type2;
        this.order_track = order_track;
    }

    public String getBoy_name() {
        return boy_name;
    }

    public void setBoy_name(String boy_name) {
        this.boy_name = boy_name;
    }

    public String getBoy_image() {
        return boy_image;
    }

    public void setBoy_image(String boy_image) {
        this.boy_image = boy_image;
    }

    public List<OtherCharge> getOther_charges() {
        return other_charges;
    }

    public void setOther_charges(List<OtherCharge> other_charges) {
        this.other_charges = other_charges;
    }

    public String getOrder_track() {
        return order_track;
    }

    public void setOrder_track(String order_track) {
        this.order_track = order_track;
    }

    public String getOrder_type2() {
        return order_type2;
    }

    public void setOrder_type2(String order_type2) {
        this.order_type2 = order_type2;
    }

    public String getSupport_phone() {
        return support_phone;
    }

    public void setSupport_phone(String support_phone) {
        this.support_phone = support_phone;
    }

    public String getDel_order_date() {
        return del_order_date;
    }

    public void setDel_order_date(String del_order_date) {
        this.del_order_date = del_order_date;
    }

    public String getDel_order_time() {
        return del_order_time;
    }

    public void setDel_order_time(String del_order_time) {
        this.del_order_time = del_order_time;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getDelivery_review() {
        return delivery_review;
    }

    public void setDelivery_review(String delivery_review) {
        this.delivery_review = delivery_review;
    }

    public String getProduct_review() {
        return product_review;
    }

    public void setProduct_review(String product_review) {
        this.product_review = product_review;
    }

    public String getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getOrder_coupon() {
        return order_coupon;
    }

    public void setOrder_coupon(String order_coupon) {
        this.order_coupon = order_coupon;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getSellerStoreImage() {
        return sellerStoreImage;
    }

    public void setSellerStoreImage(String sellerStoreImage) {
        this.sellerStoreImage = sellerStoreImage;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
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

    public String getSellerReview() {
        return sellerReview;
    }

    public void setSellerReview(String sellerReview) {
        this.sellerReview = sellerReview;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPickDate() {
        return orderPickDate;
    }

    public void setOrderPickDate(String orderPickDate) {
        this.orderPickDate = orderPickDate;
    }

    public String getOrderPickTime() {
        return orderPickTime;
    }

    public void setOrderPickTime(String orderPickTime) {
        this.orderPickTime = orderPickTime;
    }

    public Integer getOrderTotalItems() {
        return orderTotalItems;
    }

    public void setOrderTotalItems(Integer orderTotalItems) {
        this.orderTotalItems = orderTotalItems;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderFinal() {
        return orderFinal;
    }

    public void setOrderFinal(String orderFinal) {
        this.orderFinal = orderFinal;
    }

    public String getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(String orderTax) {
        this.orderTax = orderTax;
    }

    public String getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(String orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getOrderAssign() {
        return orderAssign;
    }

    public void setOrderAssign(String orderAssign) {
        this.orderAssign = orderAssign;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
