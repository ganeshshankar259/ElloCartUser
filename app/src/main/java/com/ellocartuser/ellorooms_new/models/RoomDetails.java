package com.ellocartuser.ellorooms_new.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomDetails {

    @SerializedName("room_id")
    private String roomId;
    @SerializedName("hotel_id")
    private String hotelId;
    @SerializedName("hotel_name")
    private String hotelName;
    @SerializedName("hotel_gst")
    private String hotelGst;
    @SerializedName("hotel_address")
    private String hotelAddress;
    @SerializedName("room_name")
    private String roomName;
    @SerializedName("room_image")
    private String roomImage;
    @SerializedName("room_image1")
    private String roomImage1;
    @SerializedName("total_price")
    private Integer totalPrice;
    @SerializedName("advance_amount")
    private Integer advanceAmount;
    @SerializedName("remaining_amount")
    private Integer remainingAmount;
    @SerializedName("commission")
    private String commission;
    @SerializedName("total_persons")
    private String totalPersons;
    @SerializedName("total_rooms")
    private String totalRooms;
    @SerializedName("total_gst")
    private String totalGst;
    @SerializedName("total_gst_amt")
    private String totalGstAmt;
    @SerializedName("total_save")
    private String totalSave;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("total_days")
    private String totalDays;
    @SerializedName("note")
    private String note;

    @SerializedName("room_available_hours")
    private String room_available_hours;
    @SerializedName("room_checkin")
    private String room_checkin;
    @SerializedName("room_checkout")
    private String room_checkout;
    @SerializedName("room_description")
    private String room_description;
    @SerializedName("room_price")
    private String room_price;
    @SerializedName("room_max_price")
    private String room_max_price;

    public RoomDetails(String roomId, String hotelId, String hotelName, String hotelGst, String hotelAddress, String roomName, String roomImage, String roomImage1, Integer totalPrice, Integer advanceAmount, Integer remainingAmount, String commission, String totalPersons, String totalRooms, String totalGst, String totalGstAmt, String totalSave, String startDate, String endDate, String totalDays, String note, String room_available_hours, String room_checkin, String room_checkout, String room_description, String room_price, String room_max_price) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelGst = hotelGst;
        this.hotelAddress = hotelAddress;
        this.roomName = roomName;
        this.roomImage = roomImage;
        this.roomImage1 = roomImage1;
        this.totalPrice = totalPrice;
        this.advanceAmount = advanceAmount;
        this.remainingAmount = remainingAmount;
        this.commission = commission;
        this.totalPersons = totalPersons;
        this.totalRooms = totalRooms;
        this.totalGst = totalGst;
        this.totalGstAmt = totalGstAmt;
        this.totalSave = totalSave;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.note = note;
        this.room_available_hours = room_available_hours;
        this.room_checkin = room_checkin;
        this.room_checkout = room_checkout;
        this.room_description = room_description;
        this.room_price = room_price;
        this.room_max_price = room_max_price;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoom_price() {
        return room_price;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public String getRoom_max_price() {
        return room_max_price;
    }

    public void setRoom_max_price(String room_max_price) {
        this.room_max_price = room_max_price;
    }

    public String getRoom_available_hours() {
        return room_available_hours;
    }

    public void setRoom_available_hours(String room_available_hours) {
        this.room_available_hours = room_available_hours;
    }

    public String getRoom_checkin() {
        return room_checkin;
    }

    public void setRoom_checkin(String room_checkin) {
        this.room_checkin = room_checkin;
    }

    public String getRoom_checkout() {
        return room_checkout;
    }

    public void setRoom_checkout(String room_checkout) {
        this.room_checkout = room_checkout;
    }

    public String getRoom_description() {
        return room_description;
    }

    public void setRoom_description(String room_description) {
        this.room_description = room_description;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelGst() {
        return hotelGst;
    }

    public void setHotelGst(String hotelGst) {
        this.hotelGst = hotelGst;
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

    public String getRoomImage1() {
        return roomImage1;
    }

    public void setRoomImage1(String roomImage1) {
        this.roomImage1 = roomImage1;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Integer advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(String totalPersons) {
        this.totalPersons = totalPersons;
    }

    public String getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(String totalRooms) {
        this.totalRooms = totalRooms;
    }

    public String getTotalGst() {
        return totalGst;
    }

    public void setTotalGst(String totalGst) {
        this.totalGst = totalGst;
    }

    public String getTotalGstAmt() {
        return totalGstAmt;
    }

    public void setTotalGstAmt(String totalGstAmt) {
        this.totalGstAmt = totalGstAmt;
    }

    public String getTotalSave() {
        return totalSave;
    }

    public void setTotalSave(String totalSave) {
        this.totalSave = totalSave;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
