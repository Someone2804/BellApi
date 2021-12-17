package com.bell.BellApi.dao;

import com.bell.BellApi.model.DocName;

import java.util.List;

public interface DocumentNameDao {
    DocName getByCode(String docCode);
    DocName getByName(String docName);
    List<DocName> getAll();
}
