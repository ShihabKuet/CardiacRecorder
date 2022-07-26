package com.example.cardiacrecorder;

public class bp_record_pass {
    private String sys;
    private String dia;
    private String pul;
    private String extrimity;
    private String position;
    private String date;
    private String time;
    private String comment;
    private String verdict;

    public bp_record_pass(){}

    public bp_record_pass(String sys, String dia, String pul, String extrimity, String position, String date, String time, String comment, String verdict) {
        this.sys = sys;
        this.dia = dia;
        this.pul = pul;
        this.extrimity = extrimity;
        this.position = position;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.verdict = verdict;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPul() {
        return pul;
    }

    public void setPul(String pul) {
        this.pul = pul;
    }

    public String getExtrimity() {
        return extrimity;
    }

    public void setExtrimity(String extrimity) {
        this.extrimity = extrimity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}
