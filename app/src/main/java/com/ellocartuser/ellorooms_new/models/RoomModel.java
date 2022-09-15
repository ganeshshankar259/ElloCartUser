package com.ellocartuser.ellorooms_new.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomModel {

    @SerializedName("lang_id")
    @Expose
    private String lang_id;

    @SerializedName("room_id")
    private String roomId;
    @SerializedName("hotel_name")
    private String hotelName;
    @SerializedName("hotel_address")
    private String hotelAddress;
    @SerializedName("room_name")
    private String roomName;
    @SerializedName("room_price")
    private Integer roomPrice;
    @SerializedName("room_max_price")
    private Integer roomMaxPrice;
    @SerializedName("room_save")
    private String roomSave;
    @SerializedName("room_image1")
    private String roomImage1;
    @SerializedName("room_image2")
    private String roomImage2;
    @SerializedName("room_image3")
    private String roomImage3;
    @SerializedName("room_image4")
    private String roomImage4;
    @SerializedName("room_rating")
    private String roomRating;
    @SerializedName("days")
    private String days;
    @SerializedName("room_text")
    private String room_text;
    @SerializedName("hotel_type")
    private String hotel_type;

    public RoomModel(String lang_id, String roomId, String hotelName, String hotelAddress, String roomName, Integer roomPrice, Integer roomMaxPrice, String roomSave, String roomImage1, String roomImage2, String roomImage3, String roomImage4, String roomRating, String days, String room_text, String hotel_type) {
        this.lang_id = lang_id;
        this.roomId = roomId;
        this.hotelName = hotelName;
        this.hotelAddress = hotelAddress;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.roomMaxPrice = roomMaxPrice;
        this.roomSave = roomSave;
        this.roomImage1 = roomImage1;
        this.roomImage2 = roomImage2;
        this.roomImage3 = roomImage3;
        this.roomImage4 = roomImage4;
        this.roomRating = roomRating;
        this.days = days;
        this.room_text = room_text;
        this.hotel_type = hotel_type;
    }

    public String getHotel_type() {
        return hotel_type;
    }

    public void setHotel_type(String hotel_type) {
        this.hotel_type = hotel_type;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomMaxPrice() {
        return roomMaxPrice;
    }

    public void setRoomMaxPrice(Integer roomMaxPrice) {
        this.roomMaxPrice = roomMaxPrice;
    }

    public String getRoomSave() {
        return roomSave;
    }

    public void setRoomSave(String roomSave) {
        this.roomSave = roomSave;
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

    public String getRoomRating() {
        return roomRating;
    }

    public void setRoomRating(String roomRating) {
        this.roomRating = roomRating;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRoom_text() {
        return room_text;
    }

    public void setRoom_text(String room_text) {
        this.room_text = room_text;
    }
}
