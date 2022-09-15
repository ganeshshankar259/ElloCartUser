package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSubs {

    @SerializedName("vproduct_id")
    @Expose
    public String vproductId;
    @SerializedName("sproduct_id")
    @Expose
    public String sproductId;
    @SerializedName("sproduct_p2")
    @Expose
    public String sproductP2;
    @SerializedName("sproduct_p3")
    @Expose
    public String sproductP3;
    @SerializedName("sproduct_p4")
    @Expose
    public String sproductP4;
    @SerializedName("sproduct_p5")
    @Expose
    public String sproductP5;
    @SerializedName("selected")
    @Expose
    public String selected;

    public ProductSubs(String vproductId, String sproductId, String sproductP2, String sproductP3, String sproductP4, String sproductP5, String selected) {
        this.vproductId = vproductId;
        this.sproductId = sproductId;
        this.sproductP2 = sproductP2;
        this.sproductP3 = sproductP3;
        this.sproductP4 = sproductP4;
        this.sproductP5 = sproductP5;
        this.selected = selected;
    }

    public String getVproductId() {
        return vproductId;
    }

    public void setVproductId(String vproductId) {
        this.vproductId = vproductId;
    }

    public String getSproductId() {
        return sproductId;
    }

    public void setSproductId(String sproductId) {
        this.sproductId = sproductId;
    }

    public String getSproductP2() {
        return sproductP2;
    }

    public void setSproductP2(String sproductP2) {
        this.sproductP2 = sproductP2;
    }

    public String getSproductP3() {
        return sproductP3;
    }

    public void setSproductP3(String sproductP3) {
        this.sproductP3 = sproductP3;
    }

    public String getSproductP4() {
        return sproductP4;
    }

    public void setSproductP4(String sproductP4) {
        this.sproductP4 = sproductP4;
    }

    public String getSproductP5() {
        return sproductP5;
    }

    public void setSproductP5(String sproductP5) {
        this.sproductP5 = sproductP5;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
