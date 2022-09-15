package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class Earning {


    @SerializedName("t_name")
    public String tName;
    @SerializedName("t_date")
    public String tDate;
    @SerializedName("t_type")
    public String tType;
    @SerializedName("t_amount")
    public Integer tAmount;
    @SerializedName("t_description")
    public String tDescription;

    public Earning(String tName, String tDate, String tType, Integer tAmount, String tDescription) {
        this.tName = tName;
        this.tDate = tDate;
        this.tType = tType;
        this.tAmount = tAmount;
        this.tDescription = tDescription;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public Integer gettAmount() {
        return tAmount;
    }

    public void settAmount(Integer tAmount) {
        this.tAmount = tAmount;
    }

    public String gettDescription() {
        return tDescription;
    }

    public void settDescription(String tDescription) {
        this.tDescription = tDescription;
    }
}
