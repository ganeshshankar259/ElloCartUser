package com.ellocartuser.apiservices.Responce;

import com.ellocartuser.apiservices.model.Banners;
import com.ellocartuser.apiservices.model.DetailCharge;
import com.ellocartuser.apiservices.model.Earning;
import com.ellocartuser.apiservices.model.OtherCharges;
import com.ellocartuser.apiservices.model.Stores;
import com.ellocartuser.home.adapterandmodel.ProductSearchList;
import com.ellocartuser.home.adapterandmodel.Statement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoresCatResponce {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("distance")
    @Expose
    private String distance;

    @SerializedName("offer_status")
    @Expose
    private String offer_status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("stores")
    @Expose
    private List<Stores> stores;
//only for product search
    @SerializedName("list")
    @Expose
    public List<ProductSearchList> list = null;

    //only for statement in wallet
    @SerializedName("statement")
    @Expose
    public List<Statement> statement = null;


    @SerializedName("deliver")
    @Expose
    private String deliver;

    @SerializedName("cod_status")
    @Expose
    public String cod_status;

    @SerializedName("delivery_charges")
    @Expose
    public String delivery_charges;

    //for check address

    @SerializedName("agent_id")
    @Expose
    public String agent_id;

    @SerializedName("wallet")
    @Expose
    public String wallet;

    @SerializedName("earnings")
    @Expose
    public List<Earning> earnings;

    @SerializedName("sellers")
    @Expose
    public List<Stores> sellers;

    @SerializedName("other_charges")
    @Expose
    public List<OtherCharges> other_charges;

    @SerializedName("details")
    @Expose
    public DetailCharge details;


    public StoresCatResponce(String status, String distance, String offer_status, String message, String type, String category_id, List<Stores> stores, List<ProductSearchList> list, List<Statement> statement, String deliver, String cod_status, String delivery_charges, String agent_id, String wallet, List<Earning> earnings, List<Stores> sellers, List<OtherCharges> other_charges, DetailCharge details) {
        this.status = status;
        this.distance = distance;
        this.offer_status = offer_status;
        this.message = message;
        this.type = type;
        this.category_id = category_id;
        this.stores = stores;
        this.list = list;
        this.statement = statement;
        this.deliver = deliver;
        this.cod_status = cod_status;
        this.delivery_charges = delivery_charges;
        this.agent_id = agent_id;
        this.wallet = wallet;
        this.earnings = earnings;
        this.sellers = sellers;
        this.other_charges = other_charges;
        this.details = details;
    }


    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public DetailCharge getDetails() {
        return details;
    }

    public void setDetails(DetailCharge details) {
        this.details = details;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getOffer_status() {
        return offer_status;
    }

    public void setOffer_status(String offer_status) {
        this.offer_status = offer_status;
    }

    public String getCod_status() {
        return cod_status;
    }

    public void setCod_status(String cod_status) {
        this.cod_status = cod_status;
    }

    public List<OtherCharges> getOther_charges() {
        return other_charges;
    }

    public void setOther_charges(List<OtherCharges> other_charges) {
        this.other_charges = other_charges;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public List<Stores> getSellers() {
        return sellers;
    }

    public void setSellers(List<Stores> sellers) {
        this.sellers = sellers;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public String getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(String delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public List<Statement> getStatement() {
        return statement;
    }

    public void setStatement(List<Statement> statement) {
        this.statement = statement;
    }

    public List<ProductSearchList> getList() {
        return list;
    }

    public void setList(List<ProductSearchList> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public List<Stores> getStores() {
        return stores;
    }

    public void setStores(List<Stores> stores) {
        this.stores = stores;
    }
}
