package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.SerializedName;
public class DetailCharge {

    @SerializedName("destination")
    public String destination;
    @SerializedName("distance")
    public String distance;
    @SerializedName("duration")
    public String duration;
    @SerializedName("origin")
    public String origin;

    public DetailCharge(String destination, String distance, String duration, String origin) {
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
