package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.RoomsNew.room_info.model.Gallery;
import com.ellocartuser.apiservices.model.DetailCharge;
import com.ellocartuser.apiservices.model.Earning;
import com.ellocartuser.apiservices.model.OtherCharges;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.ellorooms_new.models.Amenities;
import com.ellocartuser.ellorooms_new.models.Key;
import com.ellocartuser.ellorooms_new.models.Languages;
import com.ellocartuser.ellorooms_new.models.RoomDetails;
import com.ellocartuser.ellorooms_new.models.RoomModel;
import com.ellocartuser.home.adapterandmodel.ProductSearchList;
import com.ellocartuser.home.adapterandmodel.Statement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElloRoomFilterResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("languages")
    @Expose
    public List<Languages> languages = null;

    @SerializedName("amenities")
    @Expose
    public List<Amenities> amenities = null;
//list
    @SerializedName("rooms")
    @Expose
    public List<RoomModel> rooms = null;
//detail room_check
    @SerializedName("details")
    @Expose
    public RoomDetails details = null;

    @SerializedName("keys")
    @Expose
    public List<Key> key = null;

//get_room_detail
   // details,amenities is their
@SerializedName("gallery")
@Expose
public List<Gallery> gallery = null;


    public ElloRoomFilterResponse(String status, List<Languages> languages, List<Amenities> amenities, List<RoomModel> rooms, RoomDetails details, List<Key> key, List<Gallery> gallery) {
        this.status = status;
        this.languages = languages;
        this.amenities = amenities;
        this.rooms = rooms;
        this.details = details;
        this.key = key;
        this.gallery = gallery;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

    public List<Key> getKey() {
        return key;
    }

    public void setKey(List<Key> key) {
        this.key = key;
    }

    public RoomDetails getDetails() {
        return details;
    }

    public void setDetails(RoomDetails details) {
        this.details = details;
    }

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    public List<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenities> amenities) {
        this.amenities = amenities;
    }
}
