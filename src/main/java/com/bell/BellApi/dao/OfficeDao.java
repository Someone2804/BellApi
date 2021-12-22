package com.bell.BellApi.dao;

import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.model.Office;

import java.util.List;


/**
 * DAO for {@link Office}
 */
public interface OfficeDao {
    /**
     * Get all offices by {@link OfficeFilter}
     * @param filter
     * @return
     */
    List<Office> getAll(OfficeFilter filter);

    /**
     * Get office by id
     * @param id
     * @return
     */
    Office getById(Long id);

    /**
     * Save office
     * @param office
     */
    void save(Office office);

    /**
     * Update office
     * @param office
     */
    void update(Office office);

    /**
     * Get office reference
     * @param officeId
     * @return
     */
    Office getReference(Long officeId);
}
