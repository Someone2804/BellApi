package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.DocumentNameDao;
import com.bell.BellApi.dto.reference.response.DocNameResponse;
import com.bell.BellApi.service.DocumentNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentNameServiceImpl implements DocumentNameService {

    private final DocumentNameDao documentNameDao;

    @Autowired
    public DocumentNameServiceImpl(DocumentNameDao documentNameDao) {
        this.documentNameDao = documentNameDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocNameResponse> getAll() {
        return DocNameResponse.mapToListDto(documentNameDao.getAll());
    }
}
