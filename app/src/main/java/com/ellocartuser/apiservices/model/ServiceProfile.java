package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class ServiceProfile {

    @SerializedName("post_id")
    public String proId;
    @SerializedName("post_title")
    public String post_title;
    @SerializedName("post_user")
    public String post_user;
    @SerializedName("post_views")
    public String post_views;
    @SerializedName("post_rating")
    public String post_rating;
    @SerializedName("post_img1")
    public String post_img1;


    public ServiceProfile(String proId, String post_title,String post_user, String post_views, String post_rating, String post_img1) {
        this.proId = proId;
        this.post_title = post_title;
        this.post_user = post_user;
        this.post_views = post_views;
        this.post_rating = post_rating;
        this.post_img1 = post_img1;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_user() {
        return post_user;
    }

    public void setPost_user(String post_user) {
        this.post_user = post_user;
    }

    public String getPost_views() {
        return post_views;
    }

    public void setPost_views(String post_views) {
        this.post_views = post_views;
    }

    public String getPost_rating() {
        return post_rating;
    }

    public void setPost_rating(String post_rating) {
        this.post_rating = post_rating;
    }

    public String getPost_img1() {
        return post_img1;
    }

    public void setPost_img1(String post_img1) {
        this.post_img1 = post_img1;
    }
}
