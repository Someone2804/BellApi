package com.bell.BellApi.dao;

import com.bell.BellApi.model.Document;

import java.util.List;

public interface DocumentDao {
    void save(Document document);
    void update(Document document);
}
