package com.example.masterservices.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.masterservices.data.model.local.MasterJob;
import com.example.masterservices.data.model.local.ServiceEntity;

import java.util.List;

import kotlin.Pair;

@Dao
public interface ServiceDao extends BaseDao<ServiceEntity> {
    @Query("SELECT * from " + ServiceEntity.TABLE_NAME)
    LiveData<List<ServiceEntity>> getAllServices();
    @Query("SELECT DISTINCT master,job from " + ServiceEntity.TABLE_NAME)
    LiveData<List<MasterJob>> getAllMasters();

    @Query("SELECT * from " + ServiceEntity.TABLE_NAME + " b WHERE b.master = :master")
    LiveData<List<ServiceEntity>> getServicesByMaster(String master);


}
