package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewDates_Responce {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("post_date_times")
    @Expose
    private List<NewD> post_date_times = null;



    public NewDates_Responce(String status, List<NewD> post_date_times) {
        super();
        this.status = status;
        this.post_date_times = post_date_times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewD> getDates() {
        return post_date_times;
    }

    public void setDates(List<NewD> dates) {
        this.post_date_times = dates;
    }

}
