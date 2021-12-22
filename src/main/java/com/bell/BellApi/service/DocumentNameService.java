package com.bell.BellApi.service;

import com.bell.BellApi.dto.reference.response.DocNameResponse;

import java.util.List;

/**
 * Service for {@link com.bell.BellApi.model.DocName}
 */
public interface DocumentNameService {

    /**
     * Get all document names
     * @return {@link DocNameResponse}
     */
    List<DocNameResponse> getAll();
}
