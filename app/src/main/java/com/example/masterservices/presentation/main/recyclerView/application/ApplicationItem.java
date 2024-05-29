package com.example.masterservices.presentation.main.recyclerView.application;

public class ApplicationItem {
    String name;
    String master;
    String date;
    String price;

    public ApplicationItem(String name, String master, String date, String price) {
        this.name = name;
        this.master = master;
        this.date = date;
        this.price = price;
    }

    public String getMaster() {
        return master;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }
}
