package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TDates_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tut_id")
    @Expose
    private String tutId;
    @SerializedName("dates")
    @Expose
    private List<TDates_M> dates = null;

    public TDates_Responce(String status, String tutId, List<TDates_M> dates) {
        super();
        this.status = status;
        this.tutId = tutId;
        this.dates = dates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTutId() {
        return tutId;
    }

    public void setTutId(String tutId) {
        this.tutId = tutId;
    }

    public List<TDates_M> getDates() {
        return dates;
    }

    public void setDates(List<TDates_M> dates) {
        this.dates = dates;
    }
}
