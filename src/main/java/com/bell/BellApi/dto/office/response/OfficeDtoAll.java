package com.bell.BellApi.dto.office.response;

import com.bell.BellApi.model.Office;

import java.util.ArrayList;
import java.util.List;

public class OfficeDtoAll {
    private Long id;

    private String name;

    private Boolean isActive;

    public OfficeDtoAll(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public static OfficeDtoAll mapToDto(Office office){
        return new OfficeDtoAll(office.getId(),
                office.getName(),
                office.isActive());
    }

    public static List<OfficeDtoAll> mapToDtoList(List<Office> offices){
        List<OfficeDtoAll> result = new ArrayList<>();
        for(Office office : offices){
            result.add(mapToDto(office));
        }
        return result;
    }
}
