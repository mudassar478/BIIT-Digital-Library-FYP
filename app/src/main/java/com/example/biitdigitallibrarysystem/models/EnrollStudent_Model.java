package com.example.biitdigitallibrarysystem.models;

public class EnrollStudent_Model {
    int tid;
    String section;
    String reg_no;
    String name;

    public EnrollStudent_Model(int tid, String section, String reg_no, String name, int sid) {
        this.tid = tid;
        this.section = section;
        this.reg_no = reg_no;
        this.name = name;
        this.sid = sid;
    }

    public EnrollStudent_Model() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    int sid;
}
