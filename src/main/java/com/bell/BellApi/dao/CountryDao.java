package com.bell.BellApi.dao;

import com.bell.BellApi.model.Country;

public interface CountryDao {
    Country getByCode(String code);
}
