package com.example.jaimeprisha.Models;

public class ItemTypeModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private int imgid;

    public ItemTypeModel(String name, int imgid, String title) {
        this.name = name;
        this.imgid = imgid;
        this.title=title;
    }



    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}

