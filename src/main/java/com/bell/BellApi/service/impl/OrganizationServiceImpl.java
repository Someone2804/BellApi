package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dto.filter.OrgFilter;
import com.bell.BellApi.dto.organization.request.OrganizationRequest;
import com.bell.BellApi.dto.organization.response.OrganizationDtoAll;
import com.bell.BellApi.dto.organization.response.OrganizationDtoId;
import com.bell.BellApi.model.Organization;
import com.bell.BellApi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }


    @Override
    @Transactional
    public void add(OrganizationRequest organization) {
        organization.validateForSave();
        organizationDao.save(OrganizationRequest.mapToEntity(organization));
    }

    @Override
    @Transactional
    public void update(OrganizationRequest organization) {
        organization.validateForUpdate();
        organizationDao.update(OrganizationRequest.mapToEntity(organization));
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationDtoId getById(Long id) {
        Organization organization = organizationDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization with id " + id + " not found"));
        return OrganizationDtoId.mapToDto(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDtoAll> getAll(OrgFilter filter) {
        filter.validate();
        return OrganizationDtoAll.mapToDtoList(organizationDao.getAll(filter));
    }

}
