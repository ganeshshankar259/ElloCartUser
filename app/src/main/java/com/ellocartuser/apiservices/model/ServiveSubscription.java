package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class ServiveSubscription {
    
    @SerializedName("subsc_id")
    public Integer subscId;
    @SerializedName("subsc_name")
    public String subscName;
    @SerializedName("subsc_amount")
    public Integer subscAmount;
    @SerializedName("subsc_total")
    public Integer subscTotal;
    @SerializedName("subsc_tax")
    public Integer subscTax;
    @SerializedName("subsc_percent")
    public String subscPercent;
    @SerializedName("subsc_validity")
    public String subscValidity;
    @SerializedName("subsc_description")
    public String subscDescription;

    public ServiveSubscription(Integer subscId, String subscName, Integer subscAmount, Integer subscTotal, Integer subscTax, String subscPercent, String subscValidity, String subscDescription) {
        this.subscId = subscId;
        this.subscName = subscName;
        this.subscAmount = subscAmount;
        this.subscTotal = subscTotal;
        this.subscTax = subscTax;
        this.subscPercent = subscPercent;
        this.subscValidity = subscValidity;
        this.subscDescription = subscDescription;
    }

    public Integer getSubscTotal() {
        return subscTotal;
    }

    public void setSubscTotal(Integer subscTotal) {
        this.subscTotal = subscTotal;
    }

    public Integer getSubscId() {
        return subscId;
    }

    public void setSubscId(Integer subscId) {
        this.subscId = subscId;
    }

    public String getSubscName() {
        return subscName;
    }

    public void setSubscName(String subscName) {
        this.subscName = subscName;
    }

    public Integer getSubscAmount() {
        return subscAmount;
    }

    public void setSubscAmount(Integer subscAmount) {
        this.subscAmount = subscAmount;
    }

    public Integer getSubscTax() {
        return subscTax;
    }

    public void setSubscTax(Integer subscTax) {
        this.subscTax = subscTax;
    }

    public String getSubscPercent() {
        return subscPercent;
    }

    public void setSubscPercent(String subscPercent) {
        this.subscPercent = subscPercent;
    }

    public String getSubscValidity() {
        return subscValidity;
    }

    public void setSubscValidity(String subscValidity) {
        this.subscValidity = subscValidity;
    }

    public String getSubscDescription() {
        return subscDescription;
    }

    public void setSubscDescription(String subscDescription) {
        this.subscDescription = subscDescription;
    }
}
