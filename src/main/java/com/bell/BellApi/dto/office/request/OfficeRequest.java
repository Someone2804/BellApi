package com.bell.BellApi.dto.office.request;

import com.bell.BellApi.model.Office;

public class OfficeRequest {

    private Long id;

    private Long orgId;

    private String name;

    private String address;

    private String phone;

    private boolean isActive;

    public OfficeRequest(Long id, Long orgId, String name, String address, String phone, boolean isActive) {
        this.id = id;
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void validateForSave(){
        if(getOrgId() == null){
            throw new IllegalStateException("Missed required parameter orgId");
        }
        checkParam();
    }

    public void validateForUpdate(){
        if(getId() == null){
            throw new IllegalStateException("Missed required parameter id");
        }
        checkParam();
    }

    public static Office mapToEntity(OfficeRequest office){
        Office result = new Office();
        result.setId(office.getId());
        result.setName(office.getName());
        result.setAddress(office.getAddress());
        result.setPhone(office.getPhone());
        result.setActive(office.getActive());
        return result;
    }

    private void checkParam(){
        if(isNullOrEmpty(getName())){
            throw new IllegalStateException("Missed required parameter name");
        }
        if(isNullOrEmpty(getAddress())){
            throw new IllegalStateException("Missed required parameter address");
        }
        if(getAddress().length() > 50){
            throw new IllegalStateException("Max characters for address is 50");
        }
        if(getName().length() > 50){
            throw new IllegalStateException("Max characters for name is 50");
        }
        if(!isNullOrEmpty(getPhone()) && getPhone().length() > 30){
            throw new IllegalStateException("Max characters for phone is 30");
        }
    }

    private static boolean isNullOrEmpty(String s){
        return s == null || s.isBlank();
    }
}
