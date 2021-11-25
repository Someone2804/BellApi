package com.bell.BellApi.dao.impl.filter;

public class OrgFilter {

    private String name;

    private String inn;

    private boolean isActive = true;

    public OrgFilter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
