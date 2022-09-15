package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rooms_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("roomchildcategories")
    @Expose
    private List<Rooms_M> roomchildcategories = null;

    public Rooms_Responce(String status, List<Rooms_M> roomchildcategories) {
        super();
        this.status = status;
        this.roomchildcategories = roomchildcategories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Rooms_M> getRoomchildcategories() {
        return roomchildcategories;
    }

    public void setRoomchildcategories(List<Rooms_M> roomchildcategories) {
        this.roomchildcategories = roomchildcategories;
    }
}
