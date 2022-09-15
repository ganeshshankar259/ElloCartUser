package com.ellocartuser.rooms_old.ModelsandResponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roomcategories_M {

    @SerializedName("rcat_id")
    @Expose
    private String rcatId;
    @SerializedName("rcat_name")
    @Expose
    private String rcatName;
    @SerializedName("rcat_image1")
    @Expose
    private String rcatImage1;

    public Roomcategories_M(String rcatId, String rcatName, String rcatImage1) {
        super();
        this.rcatId = rcatId;
        this.rcatName = rcatName;
        this.rcatImage1 = rcatImage1;
    }

    public String getRcatId() {
        return rcatId;
    }

    public void setRcatId(String rcatId) {
        this.rcatId = rcatId;
    }

    public String getRcatName() {
        return rcatName;
    }

    public void setRcatName(String rcatName) {
        this.rcatName = rcatName;
    }

    public String getRcatImage1() {
        return rcatImage1;
    }

    public void setRcatImage1(String rcatImage1) {
        this.rcatImage1 = rcatImage1;
    }
}
