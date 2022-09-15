package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dates_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("roomsdata")
    @Expose
    private List<Dates_M> roomsdata = null;

    public Dates_Responce(String status, List<Dates_M> roomsdata) {
        super();
        this.status = status;
        this.roomsdata = roomsdata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Dates_M> getRoomsdata() {
        return roomsdata;
    }

    public void setRoomsdata(List<Dates_M> roomsdata) {
        this.roomsdata = roomsdata;
    }

}
