package com.example.masterservices.data.model.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = OrderOfUserEntity.TABLE_NAME)
public class OrderOfUserEntity {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = COLUMN_ID)
    Integer id = null;
    @ColumnInfo(name = COLUMN_USER_ID)
    Integer userId;
    @ColumnInfo(name = COLUMN_SERVICE_ID)
    Integer serviceId;
    @ColumnInfo(name = COLUMN_ORDER_NAME)
    String name;

    @ColumnInfo(name = COLUMN_ORDER_DATE)
    long date;


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderOfUserEntity(Integer userId,Integer serviceId, String name, long date){
        this.userId = userId;
        this.serviceId = serviceId;
        this.name = name;
        this.date = date;
    }
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ORDER_NAME = "name";
    public static final String COLUMN_ORDER_DATE = "date";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String TABLE_NAME = "orders_of_user_table";
}
