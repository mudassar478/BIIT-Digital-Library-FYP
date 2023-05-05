package com.example.biitdigitallibrarysystem.models;

public class timespendModel {
    public String lessonplan ,timedate;

    public String getLessonplan() {
        return lessonplan;
    }

    public void setLessonplan(String lessonplan) {
        this.lessonplan = lessonplan;
    }

    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }

    public timespendModel() {
    }

    public timespendModel(String lessonplan, String timedate) {
        this.lessonplan = lessonplan;
        this.timedate = timedate;
    }
}
