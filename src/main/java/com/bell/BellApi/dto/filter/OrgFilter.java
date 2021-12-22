package com.bell.BellApi.dto.filter;

public class OrgFilter {

    private String name;

    private String inn;

    private Boolean isActive;

    public OrgFilter(String name, String inn, Boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
            throw new IllegalStateException("Missed required parameter name");
        }
    }
}
