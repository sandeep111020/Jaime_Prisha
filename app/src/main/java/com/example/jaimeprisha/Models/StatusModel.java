package com.example.jaimeprisha.Models;

public class StatusModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String id,comment,date;
    public StatusModel(){

    }
    public StatusModel(String id,String comment,String date){
        this.comment=comment;
        this.date=date;
        this.id=id;
    }
}
