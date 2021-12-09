package com.bell.BellApi.service;


import com.bell.BellApi.dao.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.bell.BellApi.dto.organization.response.OrganizationDtoAll;
import com.bell.BellApi.dto.organization.response.OrganizationDtoId;

import java.util.List;

public interface OrganizationService {

    void add(OrganizationRequest organization);
    void update(OrganizationRequest organization);
    List<OrganizationDtoAll> getAll(OrgFilter filter);
    OrganizationDtoId getById(Long id);
}
