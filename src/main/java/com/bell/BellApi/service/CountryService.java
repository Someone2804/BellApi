package com.bell.BellApi.service;


import com.bell.BellApi.dto.reference.response.CountryResponse;

import java.util.List;

/**
 * Service for {@link com.bell.BellApi.model.Country}
 */
public interface CountryService {
    /**
     * Get all countries
     * @return {@link CountryResponse}
     */
    List<CountryResponse> getAll();

}
