package com.example.jaimeprisha.Models;

public class ItemModel{


    String itemname;
    String itemdesc;
    String itemprice;
    String itemtype;
    String itemcheck;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    String count;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    String itemimage;
    String model;
    String sellingprice;

    public ItemModel() {
    }

    public ItemModel(String itemname,String itemdesc,String itemprice,String itemtype,String itemcheck,String itemimage,String model,String sellingprice,String count) {
        this.itemname=itemname;
        this.itemdesc=itemdesc;
        this.itemprice=itemprice;
        this.itemtype=itemtype;
        this.itemcheck=itemcheck;
        this.itemimage=itemimage;
        this.model=model;
        this.count=count;
        this.sellingprice=sellingprice;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemcheck() {
        return itemcheck;
    }

    public void setItemcheck(String itemcheck) {
        this.itemcheck = itemcheck;
    }

    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }




}