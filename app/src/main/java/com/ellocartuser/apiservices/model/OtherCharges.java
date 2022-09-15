package com.ellocartuser.apiservices.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherCharges implements Parcelable {

    @SerializedName("p0")
    @Expose
    private String p0;

    @SerializedName("p1")
    @Expose
    private String p1;

    @SerializedName("p2")
    @Expose
    private String p2;

    @SerializedName("p3")
    @Expose
    private String p3;


    public OtherCharges(String p0, String p1, String p2, String p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    protected OtherCharges(Parcel in) {
        p0 = in.readString();
        p1 = in.readString();
        p2 = in.readString();
        p3 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(p0);
        dest.writeString(p1);
        dest.writeString(p2);
        dest.writeString(p3);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OtherCharges> CREATOR = new Creator<OtherCharges>() {
        @Override
        public OtherCharges createFromParcel(Parcel in) {
            return new OtherCharges(in);
        }

        @Override
        public OtherCharges[] newArray(int size) {
            return new OtherCharges[size];
        }
    };

    public String getP0() {
        return p0;
    }

    public void setP0(String p0) {
        this.p0 = p0;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }
}
