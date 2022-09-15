package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingsHistory_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rooms_data")
    @Expose
    private List<BookinsHistory_M> roomsData = null;
    @SerializedName("percent_amount_desc")
    @Expose
    private String percentAmountDesc;
    @SerializedName("rchild_checkin")
    @Expose
    private String rchildCheckin;
    @SerializedName("rchild_checkout")
    @Expose
    private String rchildCheckout;
    @SerializedName("ellocart_percentage")
    @Expose
    private Integer ellocartPercentage;
    @SerializedName("balance_amount")
    @Expose
    private Integer balanceAmount;
    @SerializedName("total_rooms")
    @Expose
    private Integer totalRooms;
    @SerializedName("Grand_Total")
    @Expose
    private Integer grandTotal;

    public BookingsHistory_Responce(String status, List<BookinsHistory_M> roomsData, String percentAmountDesc, String rchildCheckin, String rchildCheckout, Integer ellocartPercentage, Integer balanceAmount, Integer totalRooms, Integer grandTotal) {
        super();
        this.status = status;
        this.roomsData = roomsData;
        this.percentAmountDesc = percentAmountDesc;
        this.rchildCheckin = rchildCheckin;
        this.rchildCheckout = rchildCheckout;
        this.ellocartPercentage = ellocartPercentage;
        this.balanceAmount = balanceAmount;
        this.totalRooms = totalRooms;
        this.grandTotal = grandTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BookinsHistory_M> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<BookinsHistory_M> roomsData) {
        this.roomsData = roomsData;
    }

    public String getPercentAmountDesc() {
        return percentAmountDesc;
    }

    public void setPercentAmountDesc(String percentAmountDesc) {
        this.percentAmountDesc = percentAmountDesc;
    }

    public String getRchildCheckin() {
        return rchildCheckin;
    }

    public void setRchildCheckin(String rchildCheckin) {
        this.rchildCheckin = rchildCheckin;
    }

    public String getRchildCheckout() {
        return rchildCheckout;
    }

    public void setRchildCheckout(String rchildCheckout) {
        this.rchildCheckout = rchildCheckout;
    }

    public Integer getEllocartPercentage() {
        return ellocartPercentage;
    }

    public void setEllocartPercentage(Integer ellocartPercentage) {
        this.ellocartPercentage = ellocartPercentage;
    }

    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

}
