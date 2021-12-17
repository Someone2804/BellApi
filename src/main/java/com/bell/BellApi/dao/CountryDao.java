package com.bell.BellApi.dao;

import com.bell.BellApi.model.Country;

import java.util.List;

public interface CountryDao {
    Country getByCode(String code);
    List<Country> getAll();
}
