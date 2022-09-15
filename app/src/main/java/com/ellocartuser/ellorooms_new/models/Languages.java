package com.ellocartuser.ellorooms_new.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Languages {

    @SerializedName("lang_id")
    @Expose
    private String lang_id;

    @SerializedName("lang_title")
    @Expose
    private String lang_title;

    private int select =0;

    public Languages(String lang_id, String lang_title, int select) {
        this.lang_id = lang_id;
        this.lang_title = lang_title;
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public String getLang_id() {
        return lang_id;
    }

    public void setLang_id(String lang_id) {
        this.lang_id = lang_id;
    }

    public String getLang_title() {
        return lang_title;
    }

    public void setLang_title(String lang_title) {
        this.lang_title = lang_title;
    }
}
