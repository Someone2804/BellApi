package com.bell.BellApi.dao;

import com.bell.BellApi.dao.filter.OrgFilter;
import com.bell.BellApi.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationDao {
    List<Organization> getAll(OrgFilter filter);
    Optional<Organization> getById(Long id);
    void save(Organization organization);
    void update(Organization organization);
}
