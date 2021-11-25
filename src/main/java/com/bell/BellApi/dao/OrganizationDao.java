package com.bell.BellApi.dao;

import com.bell.BellApi.dao.impl.filter.OrgFilter;
import com.bell.BellApi.model.Organization;

import java.util.List;

public interface OrganizationDao {
    List<Organization> getAll(OrgFilter filter);
    Organization getById(Long id);
    void save(Organization organization);
    void update(Organization organization);
}
