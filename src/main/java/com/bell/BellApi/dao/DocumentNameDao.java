package com.bell.BellApi.dao;

import com.bell.BellApi.model.DocName;

import java.util.List;


/**
 * DAO for {@link DocumentNameDao}
 */
public interface DocumentNameDao {

    /**
     * Get document name by code
     * @param docCode
     * @return
     */
    DocName getByCode(String docCode);

    /**
     * Get document name by name
     * @param docName
     * @return
     */
    DocName getByName(String docName);

    /**
     * Get all documents
     * @return
     */
    List<DocName> getAll();
}
