package com.bell.BellApi.dto.filter;

public class OfficeFilter {

    private Long orgId;

    private String name;

    private String phone;

    private Boolean isActive;

    public OfficeFilter(Long orgId, String name, String phone, Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
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

    public void validate(){
        if(getOrgId() == null){
            throw new IllegalStateException("Missed required parameter orgId");
        }
    }
}
