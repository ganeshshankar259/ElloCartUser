package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class B2BStore {
    @SerializedName("b2b_id")
    @Expose
    public String b2bId;
    @SerializedName("b2b_store_name")
    @Expose
    public String b2bStoreName;
    @SerializedName("b2b_store_image")
    @Expose
    public String b2bStoreImage;
    @SerializedName("b2b_address")
    @Expose
    public String b2bAddress;
    @SerializedName("b2b_city")
    @Expose
    public String b2bCity;
    @SerializedName("b2b_state")
    @Expose
    public String b2bState;
    @SerializedName("b2b_pincode")
    @Expose
    public String b2bPincode;
    @SerializedName("b2b_wished")
    @Expose
    public String b2b_wished;

    public B2BStore(String b2bId, String b2bStoreName, String b2bStoreImage, String b2bAddress, String b2bCity, String b2bState, String b2bPincode, String b2b_wished) {
        this.b2bId = b2bId;
        this.b2bStoreName = b2bStoreName;
        this.b2bStoreImage = b2bStoreImage;
        this.b2bAddress = b2bAddress;
        this.b2bCity = b2bCity;
        this.b2bState = b2bState;
        this.b2bPincode = b2bPincode;
        this.b2b_wished = b2b_wished;
    }

    public String getB2b_wished() {
        return b2b_wished;
    }

    public void setB2b_wished(String b2b_wished) {
        this.b2b_wished = b2b_wished;
    }

    public String getB2bId() {
        return b2bId;
    }

    public void setB2bId(String b2bId) {
        this.b2bId = b2bId;
    }

    public String getB2bStoreName() {
        return b2bStoreName;
    }

    public void setB2bStoreName(String b2bStoreName) {
        this.b2bStoreName = b2bStoreName;
    }

    public String getB2bStoreImage() {
        return b2bStoreImage;
    }

    public void setB2bStoreImage(String b2bStoreImage) {
        this.b2bStoreImage = b2bStoreImage;
    }

    public String getB2bAddress() {
        return b2bAddress;
    }

    public void setB2bAddress(String b2bAddress) {
        this.b2bAddress = b2bAddress;
    }

    public String getB2bCity() {
        return b2bCity;
    }

    public void setB2bCity(String b2bCity) {
        this.b2bCity = b2bCity;
    }

    public String getB2bState() {
        return b2bState;
    }

    public void setB2bState(String b2bState) {
        this.b2bState = b2bState;
    }

    public String getB2bPincode() {
        return b2bPincode;
    }

    public void setB2bPincode(String b2bPincode) {
        this.b2bPincode = b2bPincode;
    }
}
