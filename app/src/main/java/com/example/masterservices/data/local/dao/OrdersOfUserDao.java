package com.example.masterservices.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.masterservices.data.model.local.OrderOfUserEntity;

import java.util.List;
@Dao
public interface OrdersOfUserDao extends BaseDao<OrderOfUserEntity> {
    @Query("SELECT * FROM " + OrderOfUserEntity.TABLE_NAME + " WHERE user_id = :userId")
    LiveData<List<OrderOfUserEntity>> getAllByUserId(Integer userId);
}
