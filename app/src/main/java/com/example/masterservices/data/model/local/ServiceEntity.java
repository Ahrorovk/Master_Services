package com.example.masterservices.data.model.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = ServiceEntity.TABLE_NAME)
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    public Integer id = null;

    @ColumnInfo(name = COLUMN_SERVICE_NAME)
    String name;

    @ColumnInfo(name = COLUMN_SERVICE_MASTER)
    String master;

    @ColumnInfo(name = COLUMN_SERVICE_PRICE)
    Integer price;

    @ColumnInfo(name = COLUMN_SERVICE_DESCRIPTION)
    String description;

    @ColumnInfo(name = COLUMN_SERVICE_IMAGE)
    Integer image;


    @ColumnInfo(name = COLUMN_SERVICE_MASTER_JOB)
    String job;


    public Integer getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public ServiceEntity(Integer id, String name, String master, Integer price, String description, Integer image, String job) {
        this.id = id;
        this.name = name;
        this.master = master;
        this.price = price;
        this.description = description;
        this.image = image;
        this.job = job;
    }

    @Ignore
    public ServiceEntity(String name, String master, Integer price, String description, Integer image, String job) {
        this.name = name;
        this.master = master;
        this.price = price;
        this.description = description;
        this.image = image;
        this.job = job;
    }

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICE_NAME = "name";
    public static final String COLUMN_SERVICE_MASTER = "master";
    public static final String COLUMN_SERVICE_MASTER_JOB = "job";
    public static final String COLUMN_SERVICE_PRICE = "price";
    public static final String COLUMN_SERVICE_DESCRIPTION = "description";
    public static final String COLUMN_SERVICE_IMAGE = "image";

    public static final String TABLE_NAME = "service_table";
}

