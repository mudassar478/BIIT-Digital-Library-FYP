package com.example.biitdigitallibrarysystem.models;

import com.google.gson.annotations.SerializedName;

public class UploadLessonPlanModel {
    @SerializedName("tid")
    private  int tid;

    @SerializedName("cid")
    private  int cid;

    @SerializedName("title")
    private  String title;

    @SerializedName("week")
    private  String week;

    @SerializedName("file")
    private  String file;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
