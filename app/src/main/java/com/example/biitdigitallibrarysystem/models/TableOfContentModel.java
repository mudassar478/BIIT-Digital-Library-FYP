package com.example.biitdigitallibrarysystem.models;

public class TableOfContentModel {

    String title,keywords;
    int id,bid,pageno;

    public TableOfContentModel() {
    }

    public TableOfContentModel(String title, String keywords, int id, int bid, int pageno) {
        this.title = title;
        this.keywords = keywords;
        this.id = id;
        this.bid = bid;
        this.pageno = pageno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }
}
