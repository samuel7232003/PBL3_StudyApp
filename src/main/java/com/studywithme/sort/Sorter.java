package com.studywithme.sort;

public class Sorter {

    private String sortName;
    private String sortBy;
    private String dateMeeting;
    public Sorter() {
    }

    public Sorter (String sortName, String sortBy, String dateMeeting) {
        this.sortName = sortName;
        this.sortBy = sortBy;
        this.dateMeeting = dateMeeting;
    }

    public String getSortName() {
        return this.sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(String dateMeeting) {
        this.dateMeeting = dateMeeting;
    }
}
