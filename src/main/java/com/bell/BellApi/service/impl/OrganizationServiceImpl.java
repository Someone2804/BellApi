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

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void add(OrganizationRequest organization) {
        if(organization == null){
            throw new IllegalArgumentException("OrganizationRequest is null");
        }
        organization.validateForSave();
        organizationDao.save(OrganizationRequest.mapToEntity(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationRequest organization) {
        if(organization == null){
            throw new IllegalArgumentException("OrganizationRequest is null");
        }
        organization.validateForUpdate();
        organizationDao.update(OrganizationRequest.mapToEntity(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationDtoId getById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Id is null");
        }
        Organization organization = organizationDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization with id " + id + " not found"));
        return OrganizationDtoId.mapToDto(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationDtoAll> getAll(OrgFilter filter) {
        if(filter == null){
            throw new IllegalArgumentException("OrgFilter is null");
        }
        filter.validate();
        return OrganizationDtoAll.mapToDtoList(organizationDao.getAll(filter));
    }

}
