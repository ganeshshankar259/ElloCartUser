package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.OtherServiceFOrServices;
import com.ellocartuser.home.adapterandmodel.SubcategoryStoreAll;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicePostDetailedResponce {

    @SerializedName("post_id")
    public String postId;
    @SerializedName("post_cat")
    public String postCat;
    @SerializedName("servcat_name")
    public String servcatName;
    @SerializedName("servcat_image")
    public String servcatImage;
    @SerializedName("post_scats")
    public String postScats;
    @SerializedName("servsubcategories")
    public List<SubcategoryStoreAll> servsubcategories = null;
    @SerializedName("post_title")
    public String postTitle;
    @SerializedName("post_address")
    public String postAddress;
    @SerializedName("post_description")
    public String postDescription;
    @SerializedName("post_img1")
    public String postImg1;
    @SerializedName("post_date")
    public String postDate;
    @SerializedName("post_time")
    public String postTime;
    @SerializedName("post_status")
    public String postStatus;
    @SerializedName("post_views")
    public Integer postViews;
    @SerializedName("user_name")
    public String userName;
    @SerializedName("user_image")
    public String userImage;
    @SerializedName("user_phone")
    public String userPhone;
    @SerializedName("other_services")
    public List<OtherServiceFOrServices> otherServices = null;
    @SerializedName("status")
    public String status;
    @SerializedName("post_expr")
    public String post_expr ;


    public ServicePostDetailedResponce(String postId, String postCat, String servcatName, String servcatImage, String postScats, List<SubcategoryStoreAll> servsubcategories, String postTitle, String postAddress, String postDescription, String postImg1, String postDate, String postTime, String postStatus, Integer postViews, String userName, String userImage, String userPhone, List<OtherServiceFOrServices> otherServices, String status, String post_expr) {
        this.postId = postId;
        this.postCat = postCat;
        this.servcatName = servcatName;
        this.servcatImage = servcatImage;
        this.postScats = postScats;
        this.servsubcategories = servsubcategories;
        this.postTitle = postTitle;
        this.postAddress = postAddress;
        this.postDescription = postDescription;
        this.postImg1 = postImg1;
        this.postDate = postDate;
        this.postTime = postTime;
        this.postStatus = postStatus;
        this.postViews = postViews;
        this.userName = userName;
        this.userImage = userImage;
        this.userPhone = userPhone;
        this.otherServices = otherServices;
        this.status = status;
        this.post_expr = post_expr;
    }

    public String getPost_expr() {
        return post_expr;
    }

    public void setPost_expr(String post_expr) {
        this.post_expr = post_expr;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostCat() {
        return postCat;
    }

    public void setPostCat(String postCat) {
        this.postCat = postCat;
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

    public String getPostScats() {
        return postScats;
    }

    public void setPostScats(String postScats) {
        this.postScats = postScats;
    }

    public List<SubcategoryStoreAll> getServsubcategories() {
        return servsubcategories;
    }

    public void setServsubcategories(List<SubcategoryStoreAll> servsubcategories) {
        this.servsubcategories = servsubcategories;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostImg1() {
        return postImg1;
    }

    public void setPostImg1(String postImg1) {
        this.postImg1 = postImg1;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getPostViews() {
        return postViews;
    }

    public void setPostViews(Integer postViews) {
        this.postViews = postViews;
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

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<OtherServiceFOrServices> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<OtherServiceFOrServices> otherServices) {
        this.otherServices = otherServices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

