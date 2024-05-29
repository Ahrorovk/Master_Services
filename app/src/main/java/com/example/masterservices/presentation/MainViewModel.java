package com.example.masterservices.presentation;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.masterservices.data.model.local.MasterJob;
import com.example.masterservices.data.model.local.OrderOfUserEntity;
import com.example.masterservices.data.model.local.ServiceEntity;
import com.example.masterservices.data.model.local.UserEntity;
import com.example.masterservices.domain.repository.MasterRepository;
import com.example.masterservices.domain.InsertAllServices;
import com.example.masterservices.util.Constants;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private String regEmail = "";
    private String regPassword = "";
    private String regUsername = "";
    private String regPhoneNumber = "";

    private String logEmail = "";
    private String logPassword = "";
    private Integer userId = 0;
    InsertAllServices insertAllServices;


    private void init() {
        insertAllServices.invoke();
    }

    public void setRegEmail(String email) {
        this.regEmail = email;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegPassword(String password) {
        this.regPassword = password;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegUsername(String username) {
        this.regUsername = username;
    }

    public String getRegUsername() {
        return regUsername;
    }

    public void setRegPhoneNumber(String phoneNumber) {
        this.regPhoneNumber = phoneNumber;
    }

    public String getRegPhoneNumber() {
        return regPhoneNumber;
    }

    public void setRegByTitle(String title, String state) {
        switch (title) {
            case "email":
                setRegEmail(state);
                break;
            case "username":
                setRegUsername(state);
                break;
            case "phoneNumber":
                setRegPhoneNumber(state);
                break;
            case "password":
                setRegPassword(state);
                break;
            default:
                break;
        }
    }

    public void setLogByTitle(String title, String state) {
        switch (title) {
            case "email":
                setLogEmail(state);
                break;
            case "password":
                setLogPassword(state);
                break;
            default:
                break;
        }
    }

    private final LiveData<List<ServiceEntity>> services;
    private final LiveData<List<MasterJob>> masters;
    private LiveData<List<ServiceEntity>> servicesByMaster;
    private LiveData<List<OrderOfUserEntity>> orders;
    private final MasterRepository masterRepository;
    private LiveData<UserEntity> user;
    private final LiveData<List<UserEntity>> users;
    private LiveData<UserEntity> userById;

    public MainViewModel(Application application) {
        super(application);
        masterRepository = new MasterRepository(application);
        users = masterRepository.getUsers();

        services = masterRepository.getServices();
        masters = masterRepository.getMasters();
        insertAllServices = new InsertAllServices(masterRepository);
        init();
    }


    public void getServicesByMaster(String master) {
        Log.e("TAG", "services->>>" + masterRepository.getServicesByMaster(master).toString());
        servicesByMaster = masterRepository.getServicesByMaster(master);
    }

    public void getAllOrdersByUserId(Integer userId) {
        masterRepository.getAllOrdersByUserId(userId);
        orders = masterRepository.getOrders();
    }

    public void getUserByEmail() {
        masterRepository.getUserByEmail(getLogEmail(), getLogPassword());
        user = masterRepository.getUser();
    }

    public LiveData<UserEntity> getUserByIdFromDb(Integer id) {
        Log.e("TAG","USER_ID->" + getUserId().toString());

        return masterRepository.getUserById(id);
    }

    public LiveData<UserEntity> getUserById() {
        return userById;
    }

    public LiveData<UserEntity> getUserFromVm() {
        return user;
    }

    public LiveData<List<UserEntity>> getUsersFromVm() {
        return users;
    }


    public LiveData<List<MasterJob>> getMasters() {
        return masters;
    }

    public LiveData<List<OrderOfUserEntity>> getOrdersByUserIdFromVm() {
        return orders;
    }

    public LiveData<List<ServiceEntity>> getServicesFromVm() {
        return services;
    }

    public void insertUser(UserEntity user) {
        masterRepository.insertUser(user);
    }

    public void clean() {
        setRegUsername("");
        setRegPhoneNumber("");
    }

    public void insertOrderOfUser(OrderOfUserEntity orderOfUserEntity) {
        masterRepository.insertOrderOfUser(orderOfUserEntity);
    }


    public LiveData<List<ServiceEntity>> getServicesByMaster() {
        return servicesByMaster;
    }

    public String getLogEmail() {
        return logEmail;
    }

    public void setLogEmail(String logEmail) {
        this.logEmail = logEmail;
    }

    public String getLogPassword() {
        return logPassword;
    }

    public void setLogPassword(String logPassword) {
        this.logPassword = logPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        Log.e("TAG","USER_ID->" + userId.toString());
        this.userId = userId;
    }
}

