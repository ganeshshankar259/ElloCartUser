package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delete_Cart_ById_Responce {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("bcart_userid")
    @Expose
    private String bcart_userid;
    @SerializedName("bcart_id")
    @Expose
    private String bcart_id;

    public Delete_Cart_ById_Responce(String status, String msg,String bcart_userid,String bcart_id) {
        super();
        this.status = status;
        this.msg = msg;
        this.bcart_userid = bcart_userid;
        this.bcart_id = bcart_id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getbcart_userid() {
        return bcart_userid;
    }

    public void setbcart_userid(String bcart_userid) {
        this.bcart_userid = bcart_userid;
    }

    public String getbcart_id() {
        return bcart_id;
    }

    public void setbcart_id(String bcart_id) {
        this.bcart_id = bcart_id;
    }
}
