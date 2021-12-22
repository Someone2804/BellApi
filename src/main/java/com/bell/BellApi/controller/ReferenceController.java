package com.bell.BellApi.controller;


import com.bell.BellApi.dto.reference.response.CountryResponse;
import com.bell.BellApi.dto.reference.response.DocNameResponse;
import com.bell.BellApi.service.CountryService;
import com.bell.BellApi.service.DocumentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Reference controller
 */
@RestController
@RequestMapping("/api")
public class ReferenceController {

    private final DocumentNameService documentNameService;
    private final CountryService countryService;

    @Autowired
    public ReferenceController(DocumentNameService documentNameService, CountryService countryService) {
        this.documentNameService = documentNameService;
        this.countryService = countryService;
    }

    /**
     * Mapping for /docs
     * @return docs dto's list
     */
    @GetMapping("/docs")
    public List<DocNameResponse> getAllDocuments(){
        return documentNameService.getAll();
    }

    /**
     * Mapping for /countries
     * @return countries dto's list
     */
    @GetMapping("/countries")
    public List<CountryResponse> getAllCountries(){
        return countryService.getAll();
    }

}
