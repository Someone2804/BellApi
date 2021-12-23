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
     * @return list of {@link Organization}
     */
    List<Organization> getAll(OrgFilter filter);

    /**
     * Get organization by id
     * @param id
     * @return {@link Optional} {@link Organization}
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
     * @return {@link Organization}
     */
    Organization getReference(Long id);
}
