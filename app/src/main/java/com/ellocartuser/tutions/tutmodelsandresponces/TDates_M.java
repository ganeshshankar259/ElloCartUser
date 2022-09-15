package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TDates_M {

    @SerializedName("dt_id")
    @Expose
    private String dtId;
    @SerializedName("tut_id")
    @Expose
    private String tutId;
    @SerializedName("date")
    @Expose
    private String date;

    public TDates_M(String dtId, String tutId, String date) {
        super();
        this.dtId = dtId;
        this.tutId = tutId;
        this.date = date;
    }

    public String getDtId() {
        return dtId;
    }

    public void setDtId(String dtId) {
        this.dtId = dtId;
    }

    public String getTutId() {
        return tutId;
    }

    public void setTutId(String tutId) {
        this.tutId = tutId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
