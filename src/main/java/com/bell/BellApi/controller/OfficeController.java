package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.dto.office.request.OfficeRequest;
import com.bell.BellApi.dto.office.response.OfficeDtoAll;
import com.bell.BellApi.dto.office.response.OfficeDtoId;
import com.bell.BellApi.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Office controller
 */
@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Mapping for /{id}
     * @param id
     * @return office dto
     */
    @GetMapping("/{id}")
    public OfficeDtoId getById(@PathVariable Long id){
        return officeService.getById(id);
    }

    /**
     * Mapping for /list
     * @param filter
     * @return list of office dto's
     */
    @PostMapping("/list")
    public List<OfficeDtoAll> getAll(@RequestBody OfficeFilter filter){
        return officeService.getAll(filter);
    }

    /**
     * Mapping for /add
     * @param officeRequest
     */
    @PostMapping("/add")
    public void add(@RequestBody OfficeRequest officeRequest){
        officeService.add(officeRequest);
    }

    /**
     * Mapping for /update
     * @param officeRequest
     */
    @PostMapping("/update")
    public void update(@RequestBody OfficeRequest officeRequest){
        officeService.update(officeRequest);
    }

}
