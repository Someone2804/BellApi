package com.bell.BellApi.dao;

import com.bell.BellApi.model.Organization;

import java.util.List;

public interface OrganizationDao {
    List<Organization> getAll();
    Organization getById(Long id);
    void save(Organization organization);
    void update(Organization organization);
}
