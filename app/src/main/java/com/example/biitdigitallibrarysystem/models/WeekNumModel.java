package com.example.biitdigitallibrarysystem.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeekNumModel {
    @SerializedName("Key")
    private String week ;

    @SerializedName("Value")
    private ArrayList<WeeksModel> list;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public ArrayList<WeeksModel> getList() {
        return list;
    }

    public void setList(ArrayList<WeeksModel> list) {
        this.list = list;
    }
}
