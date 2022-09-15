package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TTime_M {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date_id")
    @Expose
    private String dateId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("tut_id")
    @Expose
    private String tutId;

    public TTime_M(String time, String dateId, String date, String tutId) {
        super();
        this.time = time;
        this.dateId = dateId;
        this.date = date;
        this.tutId = tutId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateId() {
        return dateId;
    }

    public void setDateId(String dateId) {
        this.dateId = dateId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTutId() {
        return tutId;
    }

    public void setTutId(String tutId) {
        this.tutId = tutId;
    }

}
