package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dao.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.bell.BellApi.dto.office.response.OfficeDtoAll;
import com.bell.BellApi.dto.office.response.OfficeDtoId;
import com.bell.BellApi.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @Transactional
    @Override
    public void add(OfficeRequest office) {
        office.validateForSave();
        officeDao.save(OfficeRequest.mapToEntity(office), office.getOrgid());
    }

    @Transactional
    @Override
    public void update(OfficeRequest office) {
        office.validateForUpdate();
        officeDao.update(OfficeRequest.mapToEntity(office));
    }

    @Transactional
    @Override
    public List<OfficeDtoAll> getAll(OfficeFilter filter) {
        filter.validate();
        return OfficeDtoAll.mapToDtoList(officeDao.getAll(filter));
    }

    @Transactional
    @Override
    public OfficeDtoId getById(Long id) {
        return OfficeDtoId.mapToDto(officeDao.getById(id));
    }
}
