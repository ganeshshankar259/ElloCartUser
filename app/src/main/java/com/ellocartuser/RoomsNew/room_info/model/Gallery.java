
package com.ellocartuser.RoomsNew.room_info.model;

import com.google.gson.annotations.SerializedName;

public class Gallery {

    @SerializedName("room_image1")
    private String roomImage1;
    @SerializedName("room_image2")
    private String roomImage2;
    @SerializedName("room_image3")
    private String roomImage3;
    @SerializedName("room_image4")
    private String roomImage4;
    @SerializedName("room_image5")
    private String roomImage5;
    @SerializedName("room_image6")
    private String roomImage6;

    public Gallery(String roomImage1, String roomImage2, String roomImage3, String roomImage4, String roomImage5, String roomImage6) {
        this.roomImage1 = roomImage1;
        this.roomImage2 = roomImage2;
        this.roomImage3 = roomImage3;
        this.roomImage4 = roomImage4;
        this.roomImage5 = roomImage5;
        this.roomImage6 = roomImage6;
    }

    public String getRoomImage1() {
        return roomImage1;
    }

    public void setRoomImage1(String roomImage1) {
        this.roomImage1 = roomImage1;
    }

    public String getRoomImage2() {
        return roomImage2;
    }

    public void setRoomImage2(String roomImage2) {
        this.roomImage2 = roomImage2;
    }

    public String getRoomImage3() {
        return roomImage3;
    }

    public void setRoomImage3(String roomImage3) {
        this.roomImage3 = roomImage3;
    }

    public String getRoomImage4() {
        return roomImage4;
    }

    public void setRoomImage4(String roomImage4) {
        this.roomImage4 = roomImage4;
    }

    public String getRoomImage5() {
        return roomImage5;
    }

    public void setRoomImage5(String roomImage5) {
        this.roomImage5 = roomImage5;
    }

    public String getRoomImage6() {
        return roomImage6;
    }

    public void setRoomImage6(String roomImage6) {
        this.roomImage6 = roomImage6;
    }
}
