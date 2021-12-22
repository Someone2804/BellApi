package com.bell.BellApi.dto.organization.request;

import com.bell.BellApi.model.Organization;

/**
 * Request DTO for {@link Organization}
 */
public class OrganizationRequest {

    private Long id;

    private String name;

    private String fullName;

    private String inn;

    private String kpp;

    private String address;

    private String phone;

    private boolean isActive;

    public OrganizationRequest(Long id, String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Validate fields for save
     */
    public void validateForSave(){
        if(isNullOrEmpty(getName())){
            throw new IllegalStateException("Missed required parameter name");
        }
        if(isNullOrEmpty(getFullName())){
            throw new IllegalStateException("Missed required parameter fullName");
        }
        if(isNullOrEmpty(getInn())){
            throw new IllegalStateException("Missed required parameter inn");
        }
        if(isNullOrEmpty(getKpp())){
            throw new IllegalStateException("Missed required parameter kpp");
        }
        if(isNullOrEmpty(getAddress())){
            throw new IllegalStateException("Missed required parameter address");
        }
        checkFields();
    }

    /**
     * Validate fields for update
     */
    public void validateForUpdate(){
        validateForSave();
        if(getId() == null){
            throw new IllegalStateException("Missed required parameter id");
        }
        checkFields();
    }

    /**
     * Map DTO to entity
     * @param request
     * @return
     */
    public static Organization mapToEntity(OrganizationRequest request){
        return new Organization(request.getId(),
                request.getName(),
                request.getFullName(),
                request.getInn(),
                request.getKpp(),
                request.getAddress(),
                request.getPhone(),
                request.isActive());
    }

    private static boolean isNullOrEmpty(String s){
        return s == null || s.isBlank();
    }

    private void checkFields() {
        if((!isNullOrEmpty(getName())) && (getName().length() > 50)){
            throw new IllegalStateException("Max characters for Name is 50");
        }
        if((!isNullOrEmpty(getFullName())) && (getFullName().length() > 50)){
            throw new IllegalStateException("Max characters for fullName is 50");
        }
        if((!isNullOrEmpty(getInn())) && (getInn().length() > 12)){
            throw new IllegalStateException("Max characters for inn is 12");
        }
        if((!isNullOrEmpty(getKpp())) && (getKpp().length() > 9)){
            throw new IllegalStateException("Max characters for kpp is 9");
        }
        if((!isNullOrEmpty(getAddress())) && (getAddress().length() > 50)){
            throw new IllegalStateException("Max characters for address is 50");
        }
        if(!isNullOrEmpty(getPhone()) && (getPhone().length() > 30)){
            throw new IllegalStateException("Max characters for phone is 30");
        }
    }
}
