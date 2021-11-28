package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dao.impl.filter.OrgFilter;
import com.bell.BellApi.dto.request.organization.OrganizationRequest;
import com.bell.BellApi.dto.response.organization.OrganizationDtoAll;
import com.bell.BellApi.dto.response.organization.OrganizationDtoId;
import com.bell.BellApi.exception.NotFoundException;
import com.bell.BellApi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        organizationDao.save(organization.mapToEntity());
    }

    @Override
    @Transactional
    public void update(OrganizationRequest organization) {
        organization.validateForUpdate();
        organizationDao.update(organization.mapToEntity());
    }

    @Override
    public List<OrganizationDtoAll> getAll(OrgFilter filter) {
        filter.validate();
        return OrganizationDtoAll.mapToDtoList(organizationDao.getAll(filter));
    }

    @Override
    public OrganizationDtoId getById(Long id) {
        return OrganizationDtoId.mapToDto(organizationDao.getById(id)
                .orElseThrow(() -> new NotFoundException("Organization with id " + id + " not found")));
    }
}
