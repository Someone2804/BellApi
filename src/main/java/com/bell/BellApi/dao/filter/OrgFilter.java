package com.bell.BellApi.dao.filter;

public class OrgFilter {

    private String name;

    private String inn;

    private Boolean isActive = true;

    public OrgFilter(String name, String inn) {
        this.name = name;
        this.inn = inn;
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

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void validate(){
        if(getName() == null || getName().isBlank()){
            throw new IllegalStateException("Missed require parameter name");
        }
    }
}
