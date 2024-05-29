package com.example.masterservices.domain.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.masterservices.data.local.dao.OrdersOfUserDao;
import com.example.masterservices.data.local.dao.ServiceDao;
import com.example.masterservices.data.local.dao.UserDao;
import com.example.masterservices.data.local.db.MasterRoomDatabase;
import com.example.masterservices.data.model.local.MasterJob;
import com.example.masterservices.data.model.local.OrderOfUserEntity;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.data.model.local.UserEntity;

import java.util.List;

import kotlin.Pair;

public class MasterRepository {
    MasterRoomDatabase masterRoomDatabase;
    UserDao userDao;
    ServiceDao serviceDao;
    OrdersOfUserDao ordersOfUserDao;
    private final LiveData<List<UserEntity>> users;
    private final LiveData<List<ServiceEntity>> services;
    private final LiveData<List<MasterJob>> masters;

    private LiveData<List<OrderOfUserEntity>> orders;
    private LiveData<UserEntity> user;

    public MasterRepository(Application application) {
        masterRoomDatabase = MasterRoomDatabase.getDatabase(application);
        userDao = masterRoomDatabase.userDao();
        serviceDao = masterRoomDatabase.serviceDao();
        ordersOfUserDao = masterRoomDatabase.ordersOfUserDao();
        users = userDao.getAllUsers();
        services = serviceDao.getAllServices();
        masters = serviceDao.getAllMasters();
    }

    public LiveData<List<ServiceEntity>> getServicesByMaster(String master) {
        return serviceDao.getServicesByMaster(master);
    }

    public void insertUser(UserEntity user) {
        MasterRoomDatabase.databaseWriteExecutor.execute(() -> userDao.insert(user));
    }

    public void insertOrderOfUser(OrderOfUserEntity orderOfUserEntity) {
        MasterRoomDatabase.databaseWriteExecutor.execute(() -> ordersOfUserDao.insert(orderOfUserEntity));
    }

    public void insertService(ServiceEntity service) {
        MasterRoomDatabase.databaseWriteExecutor.execute(() -> serviceDao.insert(service));
    }

    public void getUserByEmail(String email, String password) {
        user = userDao.getUser(email, password);
    }


    public LiveData<UserEntity> getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public LiveData<UserEntity> getUser() {
        return user;
    }


    public LiveData<List<MasterJob>> getMasters() {
        return masters;
    }

    public void getAllOrdersByUserId(Integer userId) {
        orders = ordersOfUserDao.getAllByUserId(userId);
    }

    public LiveData<List<OrderOfUserEntity>> getOrders() {
        return orders;
    }

    public LiveData<List<UserEntity>> getUsers() {
        Log.e("TAG", users.toString());
        return users;
    }

    public LiveData<List<ServiceEntity>> getServices() {
        Log.e("TAG", services.toString());
        return services;
    }

}
