package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductVariables {

    @SerializedName("vproduct_id")
    @Expose
    public String vproductId;
    @SerializedName("vproduct_p1")
    @Expose
    public String vproductP1;
    @SerializedName("selected")
    @Expose
    public String selected;

    public ProductVariables(String vproductId, String vproductP1, String selected) {
        this.vproductId = vproductId;
        this.vproductP1 = vproductP1;
        this.selected = selected;
    }

    public String getVproductId() {
        return vproductId;
    }

    public void setVproductId(String vproductId) {
        this.vproductId = vproductId;
    }

    public String getVproductP1() {
        return vproductP1;
    }

    public void setVproductP1(String vproductP1) {
        this.vproductP1 = vproductP1;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
