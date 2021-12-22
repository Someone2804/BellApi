package com.bell.BellApi.dto.office.response;

import com.bell.BellApi.model.Office;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OfficeDtoId {

    private Long id;

    private String name;

    private String address;

    private String phone;

    @JsonProperty("isActive")
    private boolean isActive;

    public OfficeDtoId(Long id, String name, String address, String phone, boolean isActive) {
        this.id = id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static OfficeDtoId mapToDto(Office office){
        return new OfficeDtoId(office.getId(),
                office.getName(),
                office.getAddress(),
                office.getPhone(),
                office.isActive());
    }
}
