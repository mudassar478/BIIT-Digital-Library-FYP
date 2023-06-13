package com.example.biitdigitallibrarysystem.models;

import java.util.List;

public class TableOfContent {
    private String title;
    private int pageno;
    private String keywords;


    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageNumber) {
        this.pageno = pageNumber;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
