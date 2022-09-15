package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotels_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("roomsubcategories")
    @Expose
    private List<Hotels_M> roomsubcategories = null;

    public Hotels_Responce(String status, List<Hotels_M> roomsubcategories) {
        super();
        this.status = status;
        this.roomsubcategories = roomsubcategories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Hotels_M> getRoomsubcategories() {
        return roomsubcategories;
    }

    public void setRoomsubcategories(List<Hotels_M> roomsubcategories) {
        this.roomsubcategories = roomsubcategories;
    }
}
