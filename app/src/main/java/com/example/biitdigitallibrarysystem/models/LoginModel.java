package com.example.biitdigitallibrarysystem.models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("id")
    private int sid ;

    @SerializedName("name")
    private String name ;

    @SerializedName("userid")
    private String reg_no ;

    @SerializedName("password")
    private String pass;

    @SerializedName("role")
    private String role;
    private int tid;

    public LoginModel(int tid) {
        this.tid = tid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
