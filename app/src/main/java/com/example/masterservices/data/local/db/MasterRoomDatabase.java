package com.example.masterservices.data.local.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.masterservices.data.local.dao.OrdersOfUserDao;
import com.example.masterservices.data.local.dao.ServiceDao;
import com.example.masterservices.data.local.dao.UserDao;
import com.example.masterservices.data.model.local.OrderOfUserEntity;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.data.model.local.UserEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserEntity.class, ServiceEntity.class, OrderOfUserEntity.class}, version = 4, exportSchema = false)
public abstract class MasterRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract ServiceDao serviceDao();
    public abstract OrdersOfUserDao ordersOfUserDao();

    private static volatile MasterRoomDatabase masterRoomDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MasterRoomDatabase getDatabase(final Context context) {
        if (masterRoomDatabase == null) {
            synchronized (MasterRoomDatabase.class) {
                if (masterRoomDatabase == null) {
                    masterRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                                    MasterRoomDatabase.class, "master_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return masterRoomDatabase;
    }
}
