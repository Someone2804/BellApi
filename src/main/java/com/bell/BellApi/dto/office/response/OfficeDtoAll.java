package com.bell.BellApi.dto.office.response;

import com.bell.BellApi.model.Office;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for list of offices
 */
public class OfficeDtoAll {

    private Long id;

    private String name;

    @JsonProperty("isActive")
    private boolean isActive;

    public OfficeDtoAll(Long id, String name, boolean isActive) {
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

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    private static OfficeDtoAll mapToDto(Office office){
        return new OfficeDtoAll(office.getId(),
                office.getName(),
                office.isActive());
    }

    /**
     * Map list of entity's to dto list
     * @param offices
     * @return
     */
    public static List<OfficeDtoAll> mapToDtoList(List<Office> offices){
        List<OfficeDtoAll> result = new ArrayList<>();
        for(Office office : offices){
            result.add(mapToDto(office));
        }
        return result;
    }
}
