package com.example.biitdigitallibrarysystem.models;

public class Std_CoursesModel {
    String name;
    int cid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Std_CoursesModel() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Std_CoursesModel(String name, int cid) {
        this.name = name;
        this.cid = cid;
    }
}
