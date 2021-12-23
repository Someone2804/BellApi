package com.bell.BellApi.dao;

import com.bell.BellApi.model.Country;

import java.util.List;

/**
 * DAO for {@link Country}
 */
public interface CountryDao {
    /**
     * Get country by code
     * @param code
     * @return {@link Country}
     */
    Country getByCode(String code);

    /**
     * Get all countries
     * @return list of {@link Country}
     */
    List<Country> getAll();
}
