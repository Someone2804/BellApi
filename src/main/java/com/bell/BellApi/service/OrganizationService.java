package com.bell.BellApi.service;


import com.bell.BellApi.dao.impl.filter.OrgFilter;
import com.bell.BellApi.dto.request.organization.OrganizationRequest;
import com.bell.BellApi.dto.response.organization.OrganizationDtoAll;
import com.bell.BellApi.dto.response.organization.OrganizationDtoId;

import java.util.List;

public interface OrganizationService {

    void add(OrganizationRequest organization);
    void update(OrganizationRequest organization);
    List<OrganizationDtoAll> getAll(OrgFilter filter);
    OrganizationDtoId getById(Long id);
}
