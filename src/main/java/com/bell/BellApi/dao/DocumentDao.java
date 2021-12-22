package com.bell.BellApi.dao;

import com.bell.BellApi.model.Document;


/**
 * DAO for {@link Document}
 */
public interface DocumentDao {

    /**
     * Save document
     * @param document
     */
    void save(Document document);

    /**
     * Update document
     * @param document
     */
    void update(Document document);
}
