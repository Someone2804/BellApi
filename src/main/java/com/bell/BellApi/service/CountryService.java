package com.bell.BellApi.service;


import com.bell.BellApi.dto.reference.response.CountryResponse;

import java.util.List;

public interface CountryService {
    List<CountryResponse> getAll();

}
