package com.ellocartuser.apiservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {


    @SerializedName("servcat_clr")
    @Expose
    private String servcat_clr;

    @SerializedName("servcat_clr1")
    @Expose
    private String servcat_clr1;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("category_clr")
    @Expose
    private String category_clr;
//img bg clr

    @SerializedName("category_clr1")
    @Expose
    private String category_clr1;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("selected")
    @Expose
    private String selected;

//    @SerializedName("category_image")
//    @Expose
//    private String category_image;

    @SerializedName("category_image")
    @Expose
    private String category_image;


    @SerializedName("category_image2")
    @Expose
    private String category_image2;

    @SerializedName("servcat_id")
    @Expose
    private String cat_id;

    @SerializedName("servcat_name")
    @Expose
    private String cat_name;

    @SerializedName("servcat_image")
    @Expose
    private String cat_image;


    @SerializedName("category_stores")
    @Expose
    private List<Stores> category_stores;


    public Categories(String servcat_clr, String servcat_clr1, String category_id, String category_clr, String category_clr1, String category_name, String selected, String category_image, String category_image2, String cat_id, String cat_name, String cat_image, List<Stores> category_stores) {
        this.servcat_clr = servcat_clr;
        this.servcat_clr1 = servcat_clr1;
        this.category_id = category_id;
        this.category_clr = category_clr;
        this.category_clr1 = category_clr1;
        this.category_name = category_name;
        this.selected = selected;
        this.category_image = category_image;
        this.category_image2 = category_image2;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_image = cat_image;
        this.category_stores = category_stores;
    }

    public String getServcat_clr() {
        return servcat_clr;
    }

    public void setServcat_clr(String servcat_clr) {
        this.servcat_clr = servcat_clr;
    }

    public String getServcat_clr1() {
        return servcat_clr1;
    }

    public void setServcat_clr1(String servcat_clr1) {
        this.servcat_clr1 = servcat_clr1;
    }

    public String getCategory_clr1() {
        return category_clr1;
    }

    public void setCategory_clr1(String category_clr1) {
        this.category_clr1 = category_clr1;
    }

    public List<Stores> getCategory_stores() {
        return category_stores;
    }

    public void setCategory_stores(List<Stores> category_stores) {
        this.category_stores = category_stores;
    }

    public String getCategory_clr() {
        return category_clr;
    }

    public void setCategory_clr(String category_clr) {
        this.category_clr = category_clr;
    }

    public String getCategory_image2() {
        return category_image2;
    }

    public void setCategory_image2(String category_image2) {
        this.category_image2 = category_image2;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }
}
