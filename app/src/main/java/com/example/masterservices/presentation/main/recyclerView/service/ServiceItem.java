package com.example.masterservices.presentation.main.recyclerView.service;

public class ServiceItem {
    private Integer serviceId;
    private String name;
    private String master;

    private Integer image;
    private String description;
    private Integer price;

    public ServiceItem(Integer serviceId, String name, String master, String description, Integer price, Integer image) {
        this.serviceId = serviceId;
        this.name = name;
        this.master = master;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
