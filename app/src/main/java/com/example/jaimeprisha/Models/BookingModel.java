package com.example.jaimeprisha.Models;

public class BookingModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    String name, number,address,area,city,state,pin;
    String itemname;
    String itemdesc;

    String itemtype;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    String userid;


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

    public BookingModel() {
    }

    public BookingModel(String itemname,String itemdesc,String itemtype,String itemimage,String model,String sellingprice,String count,String name,String  number,String address,String area,String city,String state,String pin,String userid) {
        this.itemname=itemname;
        this.itemdesc=itemdesc;

        this.itemtype=itemtype;
        this.userid=userid;
        this.itemimage=itemimage;
        this.model=model;
        this.count=count;
        this.sellingprice=sellingprice;
        this.name=name;
        this.number=number;
        this.address=address;
        this.area=area;
        this.city=city;
        this.state=state;
        this.pin=pin;
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



    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }



    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }
}
