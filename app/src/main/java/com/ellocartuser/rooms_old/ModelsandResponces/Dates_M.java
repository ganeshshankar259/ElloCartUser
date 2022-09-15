package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dates_M {

    @SerializedName("rd_id")
    @Expose
    private String rdId;
    @SerializedName("rd_date")
    @Expose
    private String rdDate;
    @SerializedName("rd_roomid")
    @Expose
    private String rdRoomid;
    @SerializedName("rd_subcat")
    @Expose
    private String rdSubcat;
    @SerializedName("rd_childcat")
    @Expose
    private String rdChildcat;
    @SerializedName("rd_status")
    @Expose
    private String rdStatus;

    public Dates_M(String rdId, String rdDate, String rdRoomid, String rdSubcat, String rdChildcat, String rdStatus) {
        super();
        this.rdId = rdId;
        this.rdDate = rdDate;
        this.rdRoomid = rdRoomid;
        this.rdSubcat = rdSubcat;
        this.rdChildcat = rdChildcat;
        this.rdStatus = rdStatus;
    }

    public String getRdId() {
        return rdId;
    }

    public void setRdId(String rdId) {
        this.rdId = rdId;
    }

    public String getRdDate() {
        return rdDate;
    }

    public void setRdDate(String rdDate) {
        this.rdDate = rdDate;
    }

    public String getRdRoomid() {
        return rdRoomid;
    }

    public void setRdRoomid(String rdRoomid) {
        this.rdRoomid = rdRoomid;
    }

    public String getRdSubcat() {
        return rdSubcat;
    }

    public void setRdSubcat(String rdSubcat) {
        this.rdSubcat = rdSubcat;
    }

    public String getRdChildcat() {
        return rdChildcat;
    }

    public void setRdChildcat(String rdChildcat) {
        this.rdChildcat = rdChildcat;
    }

    public String getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(String rdStatus) {
        this.rdStatus = rdStatus;
    }

}
