package com.bell.BellApi.dao;

import com.bell.BellApi.model.Office;

import java.util.List;
import java.util.Optional;

public interface OfficeDao {
    List<Office> getAll();
    Optional<Office> getById(Long id);
    void save(Office office);
    void update(Office office);
}
