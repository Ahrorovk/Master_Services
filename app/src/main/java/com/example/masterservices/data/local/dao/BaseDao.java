package com.example.masterservices.data.local.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T data);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> data);

    @Update
    void update(T data);

    @Delete
    void delete(T data);
}
