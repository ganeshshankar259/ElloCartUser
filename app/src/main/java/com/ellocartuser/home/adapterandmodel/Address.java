package com.ellocartuser.home.adapterandmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {

    @SerializedName("addr_id")
    @Expose
    public String addrId;
    @SerializedName("addr_address")
    @Expose
    public String addrAddress;
    @SerializedName("addr_city")
    @Expose
    public String addrCity;
    @SerializedName("addr_pincode")
    @Expose
    public String addrPincode;
    @SerializedName("addr_name")
    @Expose
    public String addrName;
    @SerializedName("addr_phone")
    @Expose
    public String addrPhone;
    @SerializedName("addr_lat")
    @Expose
    public String addrLat;
    @SerializedName("addr_long")
    @Expose
    public String addrLong;

    @SerializedName("select")
    @Expose
    public String select="0";

    public Address(){

    }

    public Address(String addrId, String addrAddress, String addrCity, String addrPincode, String addrName, String addrPhone, String addrLat, String addrLong, String select) {
        this.addrId = addrId;
        this.addrAddress = addrAddress;
        this.addrCity = addrCity;
        this.addrPincode = addrPincode;
        this.addrName = addrName;
        this.addrPhone = addrPhone;
        this.addrLat = addrLat;
        this.addrLong = addrLong;
        this.select = select;
    }

    protected Address(Parcel in) {
        addrId = in.readString();
        addrAddress = in.readString();
        addrCity = in.readString();
        addrPincode = in.readString();
        addrName = in.readString();
        addrPhone = in.readString();
        addrLat = in.readString();
        addrLong = in.readString();
        select = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addrId);
        dest.writeString(addrAddress);
        dest.writeString(addrCity);
        dest.writeString(addrPincode);
        dest.writeString(addrName);
        dest.writeString(addrPhone);
        dest.writeString(addrLat);
        dest.writeString(addrLong);
        dest.writeString(select);
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getAddrId() {
        return addrId;
    }

    public void setAddrId(String addrId) {
        this.addrId = addrId;
    }

    public String getAddrAddress() {
        return addrAddress;
    }

    public void setAddrAddress(String addrAddress) {
        this.addrAddress = addrAddress;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrPincode() {
        return addrPincode;
    }

    public void setAddrPincode(String addrPincode) {
        this.addrPincode = addrPincode;
    }

    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    public String getAddrPhone() {
        return addrPhone;
    }

    public void setAddrPhone(String addrPhone) {
        this.addrPhone = addrPhone;
    }

    public String getAddrLat() {
        return addrLat;
    }

    public void setAddrLat(String addrLat) {
        this.addrLat = addrLat;
    }

    public String getAddrLong() {
        return addrLong;
    }

    public void setAddrLong(String addrLong) {
        this.addrLong = addrLong;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
