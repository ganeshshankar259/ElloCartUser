
package com.ellocartuser.RoomsNew.room_info.model;

import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("status")
    private String status;
    @SerializedName("roomtypes")
    private List<Roomtype> roomtypes = null;

    public Example(String status, List<Roomtype> roomtypes) {
        this.status = status;
        this.roomtypes = roomtypes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Roomtype> getRoomtypes() {
        return roomtypes;
    }

    public void setRoomtypes(List<Roomtype> roomtypes) {
        this.roomtypes = roomtypes;
    }
}
