package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_phone_code")
    @Expose
    public String userPhoneCode;
    @SerializedName("user_phone")
    @Expose
    public String userPhone;
    @SerializedName("user_image")
    @Expose
    public String userImage;
    @SerializedName("user_token")
    @Expose
    public String userToken;
    @SerializedName("user_lat")
    @Expose
    public String userLat;
    @SerializedName("user_long")
    @Expose
    public String userLong;
    @SerializedName("user_own_ref")
    @Expose
    public String userOwnRef;
    @SerializedName("user_wallet")
    @Expose
    public String userWallet;
    @SerializedName("user_email")
    @Expose
    public String user_email;


    public Profile(String userId, String userName, String userPhoneCode, String userPhone, String userImage, String userToken, String userLat, String userLong, String userOwnRef, String userWallet, String user_email) {
        this.userId = userId;
        this.userName = userName;
        this.userPhoneCode = userPhoneCode;
        this.userPhone = userPhone;
        this.userImage = userImage;
        this.userToken = userToken;
        this.userLat = userLat;
        this.userLong = userLong;
        this.userOwnRef = userOwnRef;
        this.userWallet = userWallet;
        this.user_email = user_email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneCode() {
        return userPhoneCode;
    }

    public void setUserPhoneCode(String userPhoneCode) {
        this.userPhoneCode = userPhoneCode;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLong() {
        return userLong;
    }

    public void setUserLong(String userLong) {
        this.userLong = userLong;
    }

    public String getUserOwnRef() {
        return userOwnRef;
    }

    public void setUserOwnRef(String userOwnRef) {
        this.userOwnRef = userOwnRef;
    }

    public String getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(String userWallet) {
        this.userWallet = userWallet;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
