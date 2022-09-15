package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("usrt_id")
    @Expose
    public String usrtId;
    @SerializedName("usrt_amount")
    @Expose
    public String usrtAmount;
    @SerializedName("usrt_ref")
    @Expose
    public String usrtRef;
    @SerializedName("usrt_type")
    @Expose
    public String usrtType;
    @SerializedName("usrt_date")
    @Expose
    public String usrtDate;

    @SerializedName("usrt_desc")
    @Expose
    public String usrtDesc;

    public Statement(String usrtId, String usrtAmount, String usrtRef, String usrtType, String usrtDate, String usrtDesc) {
        this.usrtId = usrtId;
        this.usrtAmount = usrtAmount;
        this.usrtRef = usrtRef;
        this.usrtType = usrtType;
        this.usrtDate = usrtDate;
        this.usrtDesc = usrtDesc;
    }

    public String getUsrtDesc() {
        return usrtDesc;
    }

    public void setUsrtDesc(String usrtDesc) {
        this.usrtDesc = usrtDesc;
    }

    public String getUsrtId() {
        return usrtId;
    }

    public void setUsrtId(String usrtId) {
        this.usrtId = usrtId;
    }

    public String getUsrtAmount() {
        return usrtAmount;
    }

    public void setUsrtAmount(String usrtAmount) {
        this.usrtAmount = usrtAmount;
    }

    public String getUsrtRef() {
        return usrtRef;
    }

    public void setUsrtRef(String usrtRef) {
        this.usrtRef = usrtRef;
    }

    public String getUsrtType() {
        return usrtType;
    }

    public void setUsrtType(String usrtType) {
        this.usrtType = usrtType;
    }

    public String getUsrtDate() {
        return usrtDate;
    }

    public void setUsrtDate(String usrtDate) {
        this.usrtDate = usrtDate;
    }
}
