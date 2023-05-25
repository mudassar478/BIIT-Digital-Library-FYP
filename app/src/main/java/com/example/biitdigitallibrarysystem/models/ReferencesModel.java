package com.example.biitdigitallibrarysystem.models;

public class ReferencesModel {

    String content,type,sourcename;
    int id,sourceid,lid;

    public ReferencesModel(){

    }

    public ReferencesModel(String content, String type, String sourcename, int id, int sourceid, int lid){

        this.content=content;
        this.type=type;
        this.sourcename=sourcename;
        this.id=id;
        this.sourceid=sourceid;
        this.lid=lid;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

}
