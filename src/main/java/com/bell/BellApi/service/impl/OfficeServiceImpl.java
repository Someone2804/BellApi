package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.bell.BellApi.dto.office.response.OfficeDtoAll;
import com.bell.BellApi.dto.office.response.OfficeDtoId;
import com.bell.BellApi.model.Office;
import com.bell.BellApi.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void add(OfficeRequest office) {
        office.validateForSave();
        Office officeEntity = OfficeRequest.mapToEntity(office);
        officeEntity.setOrganization(organizationDao.getReference(office.getOrgId()));
        officeDao.save(officeEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void update(OfficeRequest office) {
        office.validateForUpdate();
        officeDao.update(OfficeRequest.mapToEntity(office));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<OfficeDtoAll> getAll(OfficeFilter filter) {
        filter.validate();
        return OfficeDtoAll.mapToDtoList(officeDao.getAll(filter));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public OfficeDtoId getById(Long id) {
        return OfficeDtoId.mapToDto(officeDao.getById(id));
    }
}
