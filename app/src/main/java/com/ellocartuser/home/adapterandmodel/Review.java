package com.ellocartuser.home.adapterandmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable {

    @SerializedName("review_id")
    @Expose
    public String reviewId;
    @SerializedName("reviews_id")
    @Expose
    public String reviewsId;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("user_image")
    @Expose
    public String userImage;
    @SerializedName("review_rate")
    @Expose
    public String reviewRate;
    @SerializedName("review_message")
    @Expose
    public String reviewMessage;
    @SerializedName("reviews_message")
    @Expose
    public String reviewsMessage;
    @SerializedName("user_rated")
    @Expose
    public String user_rated;

    @SerializedName("reviews_rate")
    @Expose
    public String shoprateing;

    public Review(String reviewId, String reviewsId, String userId, String userName, String userImage, String reviewRate, String reviewMessage, String reviewsMessage, String user_rated, String shoprateing) {
        this.reviewId = reviewId;
        this.reviewsId = reviewsId;
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.reviewRate = reviewRate;
        this.reviewMessage = reviewMessage;
        this.reviewsMessage = reviewsMessage;
        this.user_rated = user_rated;
        this.shoprateing = shoprateing;
    }

    protected Review(Parcel in) {
        reviewId = in.readString();
        reviewsId = in.readString();
        userId = in.readString();
        userName = in.readString();
        userImage = in.readString();
        reviewRate = in.readString();
        reviewMessage = in.readString();
        reviewsMessage = in.readString();
        user_rated = in.readString();
        shoprateing = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reviewId);
        dest.writeString(reviewsId);
        dest.writeString(userId);
        dest.writeString(userName);
        dest.writeString(userImage);
        dest.writeString(reviewRate);
        dest.writeString(reviewMessage);
        dest.writeString(reviewsMessage);
        dest.writeString(user_rated);
        dest.writeString(shoprateing);
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(String reviewsId) {
        this.reviewsId = reviewsId;
    }

    public static Creator<Review> getCREATOR() {
        return CREATOR;
    }



    public String getShoprateing() {
        return shoprateing;
    }

    public void setShoprateing(String shoprateing) {
        this.shoprateing = shoprateing;
    }

    public String getReviewsMessage() {
        return reviewsMessage;
    }

    public void setReviewsMessage(String reviewsMessage) {
        this.reviewsMessage = reviewsMessage;
    }

    public String getUser_rated() {
        return user_rated;
    }

    public void setUser_rated(String user_rated) {
        this.user_rated = user_rated;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(String reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
