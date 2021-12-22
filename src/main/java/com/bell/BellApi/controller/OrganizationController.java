package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.bell.BellApi.dto.organization.response.OrganizationDtoAll;
import com.bell.BellApi.dto.organization.response.OrganizationDtoId;
import com.bell.BellApi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *  Organization controller
 */
@RestController
@RequestMapping("api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Mapping for /list
     * @param orgFilter
     * @return organization dto's list
     */
    @PostMapping("/list")
    public List<OrganizationDtoAll> getAll(@RequestBody OrgFilter orgFilter){
        List<OrganizationDtoAll> list = organizationService.getAll(orgFilter);
        return list;
    }

    /**
     * Mapping for /{id}
     * @param id
     * @return organization dto
     */
    @GetMapping("/{id}")
    public OrganizationDtoId getById(@PathVariable Long id){
        OrganizationDtoId org = organizationService.getById(id);
        return org;
    }

    /**
     * Mapping for /add
     * @param organization
     */
    @PostMapping("/add")
    public void add(@RequestBody OrganizationRequest organization){
        organizationService.add(organization);
    }

    /**
     * Mapping for /update
     * @param organization
     */
    @PostMapping("/update")
    public void update(@RequestBody OrganizationRequest organization){
        organizationService.update(organization);
    }
}
