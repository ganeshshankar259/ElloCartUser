package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.home.adapterandmodel.Profile;
import com.ellocartuser.home.adapterandmodel.PromoWallet;
import com.ellocartuser.home.adapterandmodel.Statement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyProfileResponce {


    @SerializedName("order_type2")
    public String order_type2;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("profile")
    @Expose
    public List<Profile> profile = null;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("user_id")
    @Expose
    public String userId;

    @SerializedName("user_pwallet")
    @Expose
    public String user_pwallet;

    @SerializedName("user_pwallet_image")
    @Expose
    public String user_pwallet_image;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("order_id")
    @Expose
    public String order_id;

    @SerializedName("order_date")
    @Expose
    public String order_date;

    @SerializedName("order_time")
    @Expose
    public String order_time;


    @SerializedName("wallet")
    @Expose
    public String wallet;

    @SerializedName("ellowallet list")
    @Expose
    public List<Statement> walletlist;


    @SerializedName("promowallet list")
    @Expose
    public List<PromoWallet> promowallet;

    @SerializedName("ello wallet")
    public String ello_wallet;

    @SerializedName("promo wallet")
    public String promo_wallet;

    public MyProfileResponce(String order_type2, String status, List<Profile> profile, String type, String userId, String user_pwallet, String user_pwallet_image, String message, String order_id, String order_date, String order_time, String wallet, List<Statement> walletlist, List<PromoWallet> promowallet, String ello_wallet, String promo_wallet) {
        this.order_type2 = order_type2;
        this.status = status;
        this.profile = profile;
        this.type = type;
        this.userId = userId;
        this.user_pwallet = user_pwallet;
        this.user_pwallet_image = user_pwallet_image;
        this.message = message;
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_time = order_time;
        this.wallet = wallet;
        this.walletlist = walletlist;
        this.promowallet = promowallet;
        this.ello_wallet = ello_wallet;
        this.promo_wallet = promo_wallet;
    }

    public String getEllo_wallet() {
        return ello_wallet;
    }

    public void setEllo_wallet(String ello_wallet) {
        this.ello_wallet = ello_wallet;
    }

    public String getPromo_wallet() {
        return promo_wallet;
    }

    public void setPromo_wallet(String promo_wallet) {
        this.promo_wallet = promo_wallet;
    }

    public List<PromoWallet> getPromowallet() {
        return promowallet;
    }

    public void setPromowallet(List<PromoWallet> promowallet) {
        this.promowallet = promowallet;
    }

    public String getUser_pwallet() {
        return user_pwallet;
    }

    public void setUser_pwallet(String user_pwallet) {
        this.user_pwallet = user_pwallet;
    }

    public String getUser_pwallet_image() {
        return user_pwallet_image;
    }

    public void setUser_pwallet_image(String user_pwallet_image) {
        this.user_pwallet_image = user_pwallet_image;
    }

    public List<Statement> getWalletlist() {
        return walletlist;
    }

    public void setWalletlist(List<Statement> walletlist) {
        this.walletlist = walletlist;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getOrder_type2() {
        return order_type2;
    }

    public void setOrder_type2(String order_type2) {
        this.order_type2 = order_type2;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Profile> getProfile() {
        return profile;
    }

    public void setProfile(List<Profile> profile) {
        this.profile = profile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
