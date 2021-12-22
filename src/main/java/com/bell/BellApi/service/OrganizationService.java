package com.bell.BellApi.service;


import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.bell.BellApi.dto.organization.response.OrganizationDtoAll;
import com.bell.BellApi.dto.organization.response.OrganizationDtoId;

import java.util.List;

/**
 * Service for {@link com.bell.BellApi.model.Organization}
 */
public interface OrganizationService {
    /**
     * Add organization
     * @param organization
     */
    void add(OrganizationRequest organization);

    /**
     * Update organization
     * @param organization
     */
    void update(OrganizationRequest organization);

    /**
     * Get all organizations by filter
     * @param filter
     * @return {@link OrganizationDtoAll}
     */
    List<OrganizationDtoAll> getAll(OrgFilter filter);

    /**
     * Get organization by id
     * @param id
     * @return {@link OrganizationDtoId}
     */
    OrganizationDtoId getById(Long id);
}
