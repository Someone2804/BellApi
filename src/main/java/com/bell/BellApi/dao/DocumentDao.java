package com.bell.BellApi.dao;

import com.bell.BellApi.model.Document;

public interface DocumentDao {
    void save(Document document);
    void update(Document document);
}
