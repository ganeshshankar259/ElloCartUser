package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewD {

    @SerializedName("date")
    @Expose
    private String date;

    public NewD(String date) {
        super();
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dtId) {
        this.date = date;
    }

}
