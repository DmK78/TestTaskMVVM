package com.dmk78.testtaskmvvm.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Quote {
    public Quote() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss_dd:MM:yyyy", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        this.created = currentDateAndTime;
    }

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("createdBy")
    @Expose
    public Integer createdBy;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("tagList")
    @Expose
    public List<String> tagList = null;

    private String created;

    public String getCreated() {
        return created;
    }

}