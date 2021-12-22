package com.bell.BellApi.dto.reference.response;

import com.bell.BellApi.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for list of countries
 */
public class CountryResponse {
    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static CountryResponse mapToDto(Country country){
        CountryResponse referenceResponse = new CountryResponse();
        referenceResponse.setCode(country.getCitizenshipCode());
        referenceResponse.setName(country.getCitizenshipName());
        return referenceResponse;
    }

    /**
     * Map entity list to DTO list
     * @param country
     * @return
     */
    public static List<CountryResponse> mapToListDto(List<Country> country){
        List<CountryResponse> list = new ArrayList<>();
        for(Country c : country){
            list.add(mapToDto(c));
        }
        return list;
    }
}
