
package com.ellocartuser.RoomsNew.room_info.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Roomtype {

    @SerializedName("room_id")
    private String roomId;
    @SerializedName("room_title")
    private String roomTitle;
    @SerializedName("hotel_id")
    private String hotelId;
    @SerializedName("description")
    private String description;
    @SerializedName("near_by")
    private String nearBy;
    @SerializedName("min_price")
    private String minPrice;
    @SerializedName("max_price")
    private String maxPrice;
    @SerializedName("available_hours")
    private String availableHours;
    @SerializedName("min_per_room")
    private String minPerRoom;
    @SerializedName("max_per_room")
    private String maxPerRoom;
    @SerializedName("total_per_room")
    private String totalPerRoom;
    @SerializedName("room_checkin")
    private String roomCheckin;
    @SerializedName("room_checkout")
    private String roomCheckout;
    @SerializedName("room_status")
    private String roomStatus;
    @SerializedName("room_image1")
    private String roomImage1;
    @SerializedName("gallery")
    private List<Gallery> gallery = null;
    @SerializedName("swimming pool")
    private String swimmingPool;
    @SerializedName("wifi")
    private String wifi;
    @SerializedName("ac")
    private String ac;
    @SerializedName("free parking")
    private String freeParking;
    @SerializedName("breakfast")
    private String breakfast;
    @SerializedName("spa")
    private String spa;
    @SerializedName("room service")
    private String roomService;
    @SerializedName("gym")
    private String gym;

    public Roomtype(String roomId, String roomTitle, String hotelId, String description, String nearBy, String minPrice, String maxPrice, String availableHours, String minPerRoom, String maxPerRoom, String totalPerRoom, String roomCheckin, String roomCheckout, String roomStatus, String roomImage1, List<Gallery> gallery, String swimmingPool, String wifi, String ac, String freeParking, String breakfast, String spa, String roomService, String gym) {
        this.roomId = roomId;
        this.roomTitle = roomTitle;
        this.hotelId = hotelId;
        this.description = description;
        this.nearBy = nearBy;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.availableHours = availableHours;
        this.minPerRoom = minPerRoom;
        this.maxPerRoom = maxPerRoom;
        this.totalPerRoom = totalPerRoom;
        this.roomCheckin = roomCheckin;
        this.roomCheckout = roomCheckout;
        this.roomStatus = roomStatus;
        this.roomImage1 = roomImage1;
        this.gallery = gallery;
        this.swimmingPool = swimmingPool;
        this.wifi = wifi;
        this.ac = ac;
        this.freeParking = freeParking;
        this.breakfast = breakfast;
        this.spa = spa;
        this.roomService = roomService;
        this.gym = gym;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNearBy() {
        return nearBy;
    }

    public void setNearBy(String nearBy) {
        this.nearBy = nearBy;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }

    public String getMinPerRoom() {
        return minPerRoom;
    }

    public void setMinPerRoom(String minPerRoom) {
        this.minPerRoom = minPerRoom;
    }

    public String getMaxPerRoom() {
        return maxPerRoom;
    }

    public void setMaxPerRoom(String maxPerRoom) {
        this.maxPerRoom = maxPerRoom;
    }

    public String getTotalPerRoom() {
        return totalPerRoom;
    }

    public void setTotalPerRoom(String totalPerRoom) {
        this.totalPerRoom = totalPerRoom;
    }

    public String getRoomCheckin() {
        return roomCheckin;
    }

    public void setRoomCheckin(String roomCheckin) {
        this.roomCheckin = roomCheckin;
    }

    public String getRoomCheckout() {
        return roomCheckout;
    }

    public void setRoomCheckout(String roomCheckout) {
        this.roomCheckout = roomCheckout;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomImage1() {
        return roomImage1;
    }

    public void setRoomImage1(String roomImage1) {
        this.roomImage1 = roomImage1;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public String getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getFreeParking() {
        return freeParking;
    }

    public void setFreeParking(String freeParking) {
        this.freeParking = freeParking;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getSpa() {
        return spa;
    }

    public void setSpa(String spa) {
        this.spa = spa;
    }

    public String getRoomService() {
        return roomService;
    }

    public void setRoomService(String roomService) {
        this.roomService = roomService;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }
}
