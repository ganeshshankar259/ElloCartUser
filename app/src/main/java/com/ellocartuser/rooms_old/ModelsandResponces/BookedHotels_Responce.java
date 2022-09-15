package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookedHotels_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rooms_data")
    @Expose
    private List<BookedHotels_M> roomsData = null;

    public BookedHotels_Responce(String status, List<BookedHotels_M> roomsData) {
        super();
        this.status = status;
        this.roomsData = roomsData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BookedHotels_M> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<BookedHotels_M> roomsData) {
        this.roomsData = roomsData;
    }

}
