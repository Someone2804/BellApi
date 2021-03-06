package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.CountryDao;
import com.bell.BellApi.dto.reference.response.CountryResponse;
import com.bell.BellApi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryResponse> getAll() {
        return CountryResponse.mapToListDto(countryDao.getAll());
    }
}
