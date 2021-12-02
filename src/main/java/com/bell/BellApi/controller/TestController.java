package com.bell.BellApi.controller;

import com.bell.BellApi.dao.impl.filter.OrgFilter;
import com.bell.BellApi.dto.request.organization.OrganizationRequest;
import com.bell.BellApi.dto.response.organization.OrganizationDtoAll;
import com.bell.BellApi.dto.response.organization.OrganizationDtoId;
import com.bell.BellApi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private OrganizationService organizationService;

    @Autowired
    public TestController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public List<OrganizationDtoAll> getAll(@RequestBody OrgFilter orgFilter){
        List<OrganizationDtoAll> list = organizationService.getAll(orgFilter);
        return list;
    }

    @GetMapping("/{id}")
    public OrganizationDtoId getById(@PathVariable Long id){
        OrganizationDtoId org = organizationService.getById(id);
        return org;
    }

    @PostMapping("/add")
    public void add(@RequestBody OrganizationRequest organization){
        organizationService.add(organization);
    }



}
