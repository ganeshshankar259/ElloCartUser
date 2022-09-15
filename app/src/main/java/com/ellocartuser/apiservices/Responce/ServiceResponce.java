package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.ServicePostModel;
import com.ellocartuser.apiservices.model.ServiveSubscription;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceResponce {


    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("subscriptions")
    @Expose
    public List<ServiveSubscription> subscriptions = null;

    @SerializedName("message")
    @Expose
    public String message;
//for getposts only
    @SerializedName("posts")
    @Expose
    public List<ServicePostModel> posts = null;

    public ServiceResponce(String status, List<ServiveSubscription> subscriptions, String message, List<ServicePostModel> posts) {
        this.status = status;
        this.subscriptions = subscriptions;
        this.message = message;
        this.posts = posts;
    }

    public List<ServicePostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<ServicePostModel> posts) {
        this.posts = posts;
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

    public List<ServiveSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<ServiveSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
