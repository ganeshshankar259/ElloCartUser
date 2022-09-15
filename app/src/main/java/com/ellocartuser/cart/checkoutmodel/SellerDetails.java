package com.ellocartuser.cart.checkoutmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellerDetails {


    @SerializedName("seller_type")
   String seller_type;
    @SerializedName("seller_id")
    String seller_id;
    @SerializedName("seller_promo_eligible")
    String seller_promo_eligible;

    @SerializedName("seller_promo_status")
    String seller_promo_status;

    public SellerDetails(String seller_type, String seller_id, String seller_promo_eligible, String seller_promo_status) {
        this.seller_type = seller_type;
        this.seller_id = seller_id;
        this.seller_promo_eligible = seller_promo_eligible;
        this.seller_promo_status = seller_promo_status;
    }

    public String getSeller_promo_status() {
        return seller_promo_status;
    }

    public void setSeller_promo_status(String seller_promo_status) {
        this.seller_promo_status = seller_promo_status;
    }

    public String getSeller_type() {
        return seller_type;
    }

    public void setSeller_type(String seller_type) {
        this.seller_type = seller_type;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_promo_eligible() {
        return seller_promo_eligible;
    }

    public void setSeller_promo_eligible(String seller_promo_eligible) {
        this.seller_promo_eligible = seller_promo_eligible;
    }
}
