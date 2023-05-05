package com.example.biitdigitallibrarysystem.models;

public class StudentLogModel {
    private int tid;
    private String sections;


    public void settid(int tid) {
        this.tid = tid;
    }
    public int gettid(){
        return tid;
    }

    public void setsections(String sections) {
        this.sections = sections;
    }
    public String getsections(){
        return sections;
    }

}
