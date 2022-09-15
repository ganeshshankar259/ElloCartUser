package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cartdisplay_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("rooms_data")
    @Expose
    private List<Cartdisplay_M> roomsData = null;
    @SerializedName("percent_amount_desc")
    @Expose
    private String percentAmountDesc;
    @SerializedName("rsub_title")
    @Expose
    private String rsubTitle;
    @SerializedName("rsub_id")
    @Expose
    private String rsubId;
    @SerializedName("rchild_main")
    @Expose
    private String rchildMain;
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
    public Cartdisplay_Responce(String status, List<Cartdisplay_M> roomsData, String percentAmountDesc, String rsubTitle, String rsubId, String rchildMain, Integer ellocartPercentage, Integer balanceAmount, Integer totalRooms, Integer grandTotal) {
        super();
        this.status = status;
        this.roomsData = roomsData;
        this.percentAmountDesc = percentAmountDesc;
        this.rsubTitle = rsubTitle;
        this.rsubId = rsubId;
        this.rchildMain = rchildMain;
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

    public List<Cartdisplay_M> getRoomsData() {
        return roomsData;
    }

    public void setRoomsData(List<Cartdisplay_M> roomsData) {
        this.roomsData = roomsData;
    }

    public String getPercentAmountDesc() {
        return percentAmountDesc;
    }

    public void setPercentAmountDesc(String percentAmountDesc) {
        this.percentAmountDesc = percentAmountDesc;
    }

    public String getRsubTitle() {
        return rsubTitle;
    }

    public void setRsubTitle(String rsubTitle) {
        this.rsubTitle = rsubTitle;
    }

    public String getRsubId() {
        return rsubId;
    }

    public void setRsubId(String rsubId) {
        this.rsubId = rsubId;
    }

    public String getRchildMain() {
        return rchildMain;
    }

    public void setRchildMain(String rchildMain) {
        this.rchildMain = rchildMain;
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
