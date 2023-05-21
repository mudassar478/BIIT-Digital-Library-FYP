package com.example.biitdigitallibrarysystem.models;

import java.util.List;

public class TableOfContent {
    private String title;
    private int pageNumber;
    private List<String> keywords;

    // Constructor
    public TableOfContent(String title, int pageNumber, List<String> keywords) {
        this.title = title;
        this.pageNumber = pageNumber;
        this.keywords = keywords;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
