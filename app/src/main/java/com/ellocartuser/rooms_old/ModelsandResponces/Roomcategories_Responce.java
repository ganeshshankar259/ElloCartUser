package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Roomcategories_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("roomcategories")
    @Expose
    private List<Roomcategories_M> roomcategories = null;

    public Roomcategories_Responce(String status, List<Roomcategories_M> roomcategories) {
        super();
        this.status = status;
        this.roomcategories = roomcategories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Roomcategories_M> getRoomcategories() {
        return roomcategories;
    }

    public void setRoomcategories(List<Roomcategories_M> roomcategories) {
        this.roomcategories = roomcategories;
    }
}
