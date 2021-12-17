package com.bell.BellApi.dao;

import com.bell.BellApi.model.DocName;

public interface DocumentNameDao {
    DocName getByCode(String docCode);
    DocName getByName(String docName);
}
