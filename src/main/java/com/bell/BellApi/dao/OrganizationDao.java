package com.bell.BellApi.dao;

import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.model.Organization;

import java.util.List;
import java.util.Optional;


/**
 * DAO for {@link Organization}
 */
public interface OrganizationDao {

    /**
     * Get all organizations by {@link OrgFilter}
     * @param filter
     * @return
     */
    List<Organization> getAll(OrgFilter filter);

    /**
     * Get organization by id
     * @param id
     * @return
     */
    Optional<Organization> getById(Long id);

    /**
     * Save organization
     * @param organization
     */
    void save(Organization organization);

    /**
     * Update organization
     * @param organization
     */
    void update(Organization organization);

    /**
     * Get organization reference
     * @param id
     * @return
     */
    Organization getReference(Long id);
}
