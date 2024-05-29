package com.example.masterservices.data.model.local;

public class MasterJob {
    public String master;
    public String job;

    public MasterJob(String master, String job) {
        this.master = master;
        this.job = job;
    }

    public String getMaster() {
        return master;
    }

    public String getJob() {
        return job;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
