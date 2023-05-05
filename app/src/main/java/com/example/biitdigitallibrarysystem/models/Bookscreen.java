package com.example.biitdigitallibrarysystem.models;

public class Bookscreen {
    public String books , arid;

    public Bookscreen(String books) {
        this.books = books;
    }
    public Bookscreen(String books, String arid) {
        this.books = books;
        this.arid = arid;
    }

    public String getArid() {
        return arid;
    }

    public void setArid(String arid) {
        this.arid = arid;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }
}
