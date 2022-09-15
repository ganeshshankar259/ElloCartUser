package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TDTimes_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tut_id")
    @Expose
    private String tutId;
    @SerializedName("dates_time")
    @Expose
    private List<TTime_M> datesTime = null;
    public TDTimes_Responce(String status, String tutId, List<TTime_M> datesTime) {
        super();
        this.status = status;
        this.tutId = tutId;
        this.datesTime = datesTime;
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

    public List<TTime_M> getDatesTime() {
        return datesTime;
    }

    public void setDatesTime(List<TTime_M> datesTime) {
        this.datesTime = datesTime;
    }
}
