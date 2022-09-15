package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Availdates_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rooms_avialble")
    @Expose
    private Integer roomsAvialble;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("booked_rooms_count")
    @Expose
    private Integer bookedRoomsCount;
    @SerializedName("total_rooms_child")
    @Expose
    private String totalRoomsChild;

    public Availdates_Responce(String status, Integer roomsAvialble, String date, Integer bookedRoomsCount, String totalRoomsChild) {
        super();
        this.status = status;
        this.roomsAvialble = roomsAvialble;
        this.date = date;
        this.bookedRoomsCount = bookedRoomsCount;
        this.totalRoomsChild = totalRoomsChild;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoomsAvialble() {
        return roomsAvialble;
    }

    public void setRoomsAvialble(Integer roomsAvialble) {
        this.roomsAvialble = roomsAvialble;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBookedRoomsCount() {
        return bookedRoomsCount;
    }

    public void setBookedRoomsCount(Integer bookedRoomsCount) {
        this.bookedRoomsCount = bookedRoomsCount;
    }

    public String getTotalRoomsChild() {
        return totalRoomsChild;
    }

    public void setTotalRoomsChild(String totalRoomsChild) {
        this.totalRoomsChild = totalRoomsChild;
    }
}
