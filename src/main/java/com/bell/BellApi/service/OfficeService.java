package com.bell.BellApi.service;


import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.bell.BellApi.dto.office.response.OfficeDtoAll;
import com.bell.BellApi.dto.office.response.OfficeDtoId;

import java.util.List;


/**
 * Service for {@link com.bell.BellApi.model.Office}
 */
public interface OfficeService {

    /**
     * Add office
     * @param officeRequest
     */
    void add(OfficeRequest officeRequest);

    /**
     * Update office
     * @param officeRequest
     */
    void update(OfficeRequest officeRequest);

    /**
     * Get all offices by filter
     * @param filter
     * @return {@link OfficeDtoAll}
     */
    List<OfficeDtoAll> getAll(OfficeFilter filter);

    /**
     * Get office by id
     * @param id
     * @return {@link OfficeDtoId}
     */
    OfficeDtoId getById(Long id);
}
