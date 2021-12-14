package com.bell.BellApi.dao;

import com.bell.BellApi.model.DocName;

public interface DocumentNameDao {
    DocName getByNameAndCode(String docName, String docCode);
}
