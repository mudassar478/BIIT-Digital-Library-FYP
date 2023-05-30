package com.example.biitdigitallibrarysystem.models;

import com.google.gson.annotations.SerializedName;

public class YourDataModel {
    @SerializedName("sourceid")
    private int sourceId;

    @SerializedName("lid")
    private int lid;

    public YourDataModel() {
    }

    @SerializedName("content")
    private String content;

    @SerializedName("type")
    private String type;

    @SerializedName("sourcename")
    private String sourceName;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
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

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
