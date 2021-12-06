package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dao.filter.OrgFilter;
import com.bell.BellApi.dto.request.organization.OrganizationRequest;
import com.bell.BellApi.dto.response.organization.OrganizationDtoAll;
import com.bell.BellApi.dto.response.organization.OrganizationDtoId;
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
    @Transactional
    public OrganizationDtoId getById(Long id) {
        Organization organization = organizationDao.getById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organization with id " + id + " not found"));
        if(!organization.isActive()){
            throw new EntityNotFoundException("Organization with id " + id + " not found");
        }
        return OrganizationDtoId.mapToDto(organization);
    }

    @Override
    @Transactional
    public List<OrganizationDtoAll> getAll(OrgFilter filter) {
        filter.validate();
        return OrganizationDtoAll.mapToDtoList(organizationDao.getAll(filter));
    }


}
