package com.bell.BellApi.dto.organization.response;

import com.bell.BellApi.model.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationDtoAll {
    private Long id;
    private String name;
    private boolean isActive;

    public OrganizationDtoAll(Long id, String name, boolean isActive) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public static OrganizationDtoAll mapToDto(Organization organization){
        return new OrganizationDtoAll(organization.getId(),
                                    organization.getName(),
                                    organization.isActive());
    }

    public static List<OrganizationDtoAll> mapToDtoList(List<Organization> organizations){
        List<OrganizationDtoAll> result = new ArrayList<>();
        for(Organization organization : organizations){
            result.add(mapToDto(organization));
        }
        return result;
    }
}
