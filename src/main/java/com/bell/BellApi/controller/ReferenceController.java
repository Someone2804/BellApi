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

    @GetMapping("/docs")
    public List<DocNameResponse> getAllDocuments(){
        return documentNameService.getAll();
    }

    @GetMapping("/countries")
    public List<CountryResponse> getAllCountries(){
        return countryService.getAll();
    }

}
