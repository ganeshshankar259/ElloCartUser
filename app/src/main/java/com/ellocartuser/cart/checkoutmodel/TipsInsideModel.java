package com.ellocartuser.cart.checkoutmodel;

import com.google.gson.annotations.SerializedName;

public class TipsInsideModel {

    @SerializedName("status")
    private String status;
    @SerializedName("selected")
    private String selected;
    @SerializedName("option")
    private String option;  // 0 custom
    @SerializedName("amt")
    private String amt;

    public TipsInsideModel(String status, String selected, String option, String amt) {
        this.status = status;
        this.selected = selected;
        this.option = option;
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }
}
