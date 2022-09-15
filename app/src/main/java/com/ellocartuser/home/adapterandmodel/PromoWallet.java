package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromoWallet {

    @SerializedName("promo_id")
    @Expose
    public String prome_id;

    @SerializedName("promo_amount")
    @Expose
    public String prome_amount;
    @SerializedName("promo_ref")
    @Expose
    public String prome_ref;
    @SerializedName("promo_date")
    @Expose
    public String prome_date;

    public PromoWallet(String prome_id, String prome_amount, String prome_ref, String prome_date) {
        this.prome_id = prome_id;
        this.prome_amount = prome_amount;
        this.prome_ref = prome_ref;
        this.prome_date = prome_date;
    }

    public String getProme_id() {
        return prome_id;
    }

    public void setProme_id(String prome_id) {
        this.prome_id = prome_id;
    }

    public String getProme_amount() {
        return prome_amount;
    }

    public void setProme_amount(String prome_amount) {
        this.prome_amount = prome_amount;
    }

    public String getProme_ref() {
        return prome_ref;
    }

    public void setProme_ref(String prome_ref) {
        this.prome_ref = prome_ref;
    }

    public String getProme_date() {
        return prome_date;
    }

    public void setProme_date(String prome_date) {
        this.prome_date = prome_date;
    }
}
