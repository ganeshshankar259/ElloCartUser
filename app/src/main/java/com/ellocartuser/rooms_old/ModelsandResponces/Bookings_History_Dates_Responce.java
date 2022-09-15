package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bookings_History_Dates_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("rooms_data")
    @Expose
    private List<Bookings_History_Dates_M> roomsData = null;

    public Bookings_History_Dates_Responce(String status, String msg, List<Bookings_History_Dates_M> roomsData) {
        super();
        this.status = status;
        this.msg = msg;
        this.roomsData = roomsData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Bookings_History_Dates_M> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<Bookings_History_Dates_M> roomsData) {
        this.roomsData = roomsData;
    }

}
