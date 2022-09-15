package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Address;
import com.ellocartuser.home.adapterandmodel.AddressFriend;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressResponce {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("address")
    @Expose
    public List<Address> address = null;

    @SerializedName("receiver_address")
    @Expose
    public List<AddressFriend> receiver_address = null;
    @SerializedName("places")
    @Expose
    public List<Address> places = null;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("user_id")
    @Expose
    public String userId;

    public AddressResponce(String status, List<Address> address, List<AddressFriend> receiver_address, List<Address> places, String type, String userId) {
        this.status = status;
        this.address = address;
        this.receiver_address = receiver_address;
        this.places = places;
        this.type = type;
        this.userId = userId;
    }

    public List<AddressFriend> getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(List<AddressFriend> receiver_address) {
        this.receiver_address = receiver_address;
    }

    public List<Address> getPlaces() {
        return places;
    }

    public void setPlaces(List<Address> places) {
        this.places = places;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
