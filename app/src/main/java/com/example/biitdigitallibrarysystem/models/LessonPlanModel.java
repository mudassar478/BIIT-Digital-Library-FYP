package com.example.biitdigitallibrarysystem.models;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.biitdigitallibrarysystem.adapters.LessonPlanAdapter;
import com.example.biitdigitallibrarysystem.teacherActivities.LessonPlanActivity;

public class LessonPlanModel {

    int lid,tid,cid,page_no;
    String title, path, week;

    public int getLid() {
        return lid;
    }

    public LessonPlanModel() {
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public LessonPlanModel(int lid, int tid, int cid, int page_no, String title, String path, String week) {
        this.lid = lid;
        this.tid = tid;
        this.cid = cid;
        this.page_no = page_no;
        this.title = title;
        this.path = path;
        this.week = week;
    }

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

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
