package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.ServiceProfile;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServiceProfileResponce {

    @SerializedName("status")
    public String status;
    @SerializedName("posts")
    public List<ServiceProfile> profiles = null;
    @SerializedName("type")
    public String type;
    @SerializedName("scat_id")
    public String scatId;
    @SerializedName("message")
    public String message;

    public ServiceProfileResponce(String status, List<ServiceProfile> profiles, String type, String scatId, String message) {
        this.status = status;
        this.profiles = profiles;
        this.type = type;
        this.scatId = scatId;
        this.message = message;
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

    public List<ServiceProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ServiceProfile> profiles) {
        this.profiles = profiles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScatId() {
        return scatId;
    }

    public void setScatId(String scatId) {
        this.scatId = scatId;
    }
}
