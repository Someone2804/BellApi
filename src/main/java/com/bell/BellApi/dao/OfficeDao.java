package com.bell.BellApi.dao;

import com.bell.BellApi.model.Office;

import java.util.List;

public interface OfficeDao {
    List<Office> getAll();
    Office getById(Long id);
    void save(Office office);
    void update(Office office);
}
