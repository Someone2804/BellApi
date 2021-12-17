package com.bell.BellApi.service;

import com.bell.BellApi.dto.reference.response.DocNameResponse;

import java.util.List;

public interface DocumentNameService {
    List<DocNameResponse> getAll();
}
