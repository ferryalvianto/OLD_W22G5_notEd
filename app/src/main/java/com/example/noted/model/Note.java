package com.example.noted.model;

public class Note {
    private int noteid;
    private String notetitle;
    private String notecontent;
    private String username;

    public Note() {
    }

    public Note(int noteid, String notetitle, String notecontent, String username) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notecontent = notecontent;
        this.username = username;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotecontent() {
        return notecontent;
    }

    public void setNotecontent(String notecontent) {
        this.notecontent = notecontent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
