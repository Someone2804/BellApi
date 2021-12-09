package com.bell.BellApi.dto.office.request;

import com.bell.BellApi.model.Office;

public class OfficeRequest {

    private Long id;

    private Long orgid;

    private String name;

    private String address;

    private String phone;

    private Boolean isActive = true;

    public OfficeRequest(Long id, Long orgid, String name, String address, String phone, Boolean isActive) {
        this.id = id;
        this.orgid = orgid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        if(isActive != null) {
            this.isActive = isActive;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void validateForSave(){
        if(getOrgid() == null){
            throw new IllegalStateException("Missing required parameter orgId");
        }
        checkParam();
    }

    public void validateForUpdate(){
        if(getId() == null){
            throw new IllegalStateException("Missing required parameter id");
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
        if(getName() == null){
            throw new IllegalStateException("Missing required parameter name");
        }
        if(getAddress() == null){
            throw new IllegalStateException("Missing required parameter address");
        }
    }
}
