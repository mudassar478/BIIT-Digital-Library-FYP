package com.example.biitdigitallibrarysystem.models;

import com.google.gson.annotations.SerializedName;

public class LibraryBook {
    @SerializedName("bid")
    private int bid;

    @SerializedName("tid")
    private int tid;

    @SerializedName("sourcerole")
    private String sourcerole;

    @SerializedName("title")
    private String title;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getSourcerole() {
        return sourcerole;
    }

    public void setSourcerole(String sourcerole) {
        this.sourcerole = sourcerole;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAbstractBook() {
        return abstractBook;
    }

    public void setAbstractBook(String abstractBook) {
        this.abstractBook = abstractBook;
    }

    public String getPdf_path() {
        return pdf_path;
    }

    public void setPdf_path(String pdf_path) {
        this.pdf_path = pdf_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPage_no() {
        return page_no;
    }

    public void setPage_no(String page_no) {
        this.page_no = page_no;
    }

    @SerializedName("author")
    private String author;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("isbn")
    private String isbn;

    @SerializedName("abstract")
    private String abstractBook;

    @SerializedName("path")
    private String pdf_path;

    @SerializedName("image_path")
    private String image_path;

    @SerializedName("page_no")
    private String page_no;
}
