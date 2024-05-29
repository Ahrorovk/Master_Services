package com.example.masterservices.data.model.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = UserEntity.TABLE_NAME)
public class UserEntity {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = COLUMN_ID)
    Integer id = null;
    @ColumnInfo(name = COLUMN_NAME)
    String name;
    @ColumnInfo(name = COLUMN_EMAIL)
    String email;
    @ColumnInfo(name = COLUMN_PHONE_NUMBER)
    String phoneNumber;
    @ColumnInfo(name = COLUMN_PASSWORD)
    String password;
    public static final String TABLE_NAME = "user_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    @Ignore
    public UserEntity(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public UserEntity(Integer id, String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
