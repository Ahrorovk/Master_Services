package com.example.masterservices.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.masterservices.data.model.local.UserEntity;

import java.util.List;

@Dao
public interface UserDao extends BaseDao<UserEntity> {
    @Query("SELECT * from " + UserEntity.TABLE_NAME + " b WHERE b.email = :email AND b.password = :password")
    LiveData<UserEntity> getUser(String email, String password);

    @Query("SELECT * from " + UserEntity.TABLE_NAME + " b WHERE b.id = :id")
    LiveData<UserEntity> getUserById(Integer id);

    @Query("SELECT * from " + UserEntity.TABLE_NAME)
    LiveData<List<UserEntity>> getAllUsers();
}
