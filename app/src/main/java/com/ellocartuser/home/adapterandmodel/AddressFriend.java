package com.ellocartuser.home.adapterandmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressFriend implements Parcelable {

    @SerializedName("receiver_id")
    @Expose
    public String addrId;
    @SerializedName("receiver_address")
    @Expose
    public String addrAddress;
    @SerializedName("receiver_city")
    @Expose
    public String addrCity;
    @SerializedName("receiver_pincode")
    @Expose
    public String addrPincode;
    @SerializedName("receiver_name")
    @Expose
    public String addrName;
    @SerializedName("receiver_phone")
    @Expose
    public String addrPhone;
    @SerializedName("receiver_lat")
    @Expose
    public String addrLat;
    @SerializedName("receiver_long")
    @Expose
    public String addrLong;

    @SerializedName("select")
    @Expose
    public String select="0";

    public AddressFriend(){

    }

    public AddressFriend(String addrId, String addrAddress, String addrCity, String addrPincode, String addrName, String addrPhone, String addrLat, String addrLong, String select) {
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

    protected AddressFriend(Parcel in) {
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

    public static final Creator<AddressFriend> CREATOR = new Creator<AddressFriend>() {
        @Override
        public AddressFriend createFromParcel(Parcel in) {
            return new AddressFriend(in);
        }

        @Override
        public AddressFriend[] newArray(int size) {
            return new AddressFriend[size];
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
