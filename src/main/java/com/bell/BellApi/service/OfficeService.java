package com.bell.BellApi.service;


import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.bell.BellApi.dto.office.response.OfficeDtoAll;
import com.bell.BellApi.dto.office.response.OfficeDtoId;

import java.util.List;

public interface OfficeService {

    void add(OfficeRequest organization);
    void update(OfficeRequest organization);
    List<OfficeDtoAll> getAll(OfficeFilter filter);
    OfficeDtoId getById(Long id);
}
