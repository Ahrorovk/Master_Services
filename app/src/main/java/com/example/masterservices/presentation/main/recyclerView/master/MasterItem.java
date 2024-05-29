package com.example.masterservices.presentation.main.recyclerView.master;

public class MasterItem {
    private String master;
    private String job;

    public MasterItem(String master, String job) {

        this.master = master;
        this.job = job;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
