package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;

public class ServicePostModel {


    @SerializedName("post_id")
    public String postId;
    @SerializedName("post_title")
    public String postTitle;
    @SerializedName("post_img1")
    public String postImg1;
    @SerializedName("servcat_name")
    public String servcatName;
    @SerializedName("servcat_image")
    public String servcatImage;
    @SerializedName("post_views")
    public String post_views;

    public ServicePostModel(String postId, String postTitle, String postImg1, String servcatName, String servcatImage, String post_views) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postImg1 = postImg1;
        this.servcatName = servcatName;
        this.servcatImage = servcatImage;
        this.post_views = post_views;
    }

    public String getPost_views() {
        return post_views;
    }

    public void setPost_views(String post_views) {
        this.post_views = post_views;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostImg1() {
        return postImg1;
    }

    public void setPostImg1(String postImg1) {
        this.postImg1 = postImg1;
    }

    public String getServcatName() {
        return servcatName;
    }

    public void setServcatName(String servcatName) {
        this.servcatName = servcatName;
    }

    public String getServcatImage() {
        return servcatImage;
    }

    public void setServcatImage(String servcatImage) {
        this.servcatImage = servcatImage;
    }
}
