package com.ellocartuser.tutions.tutmodelsandresponces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TAppointListBUid_Responce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Appointment_Details")
    @Expose
    private List<TAppointListBUid_M> appointmentDetails = null;

    public TAppointListBUid_Responce(String status, List<TAppointListBUid_M> appointmentDetails) {
        super();
        this.status = status;
        this.appointmentDetails = appointmentDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TAppointListBUid_M> getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(List<TAppointListBUid_M> appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }
}
