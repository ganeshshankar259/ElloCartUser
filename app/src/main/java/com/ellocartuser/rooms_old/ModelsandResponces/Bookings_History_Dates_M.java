package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bookings_History_Dates_M {

    @SerializedName("b_orderid")
    @Expose
    private String bOrderid;
    @SerializedName("b_created")
    @Expose
    private String bCreated;

    public Bookings_History_Dates_M(String bOrderid, String bCreated) {
        super();
        this.bOrderid = bOrderid;
        this.bCreated = bCreated;
    }

    public String getbOrderid() {
        return bOrderid;
    }

    public void setbOrderid(String bOrderid) {
        this.bOrderid = bOrderid;
    }

    public String getbCreated() {
        return bCreated;
    }

    public void setbCreated(String bCreated) {
        this.bCreated = bCreated;
    }
}
