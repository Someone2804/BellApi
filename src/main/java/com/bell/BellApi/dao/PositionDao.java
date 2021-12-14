package com.bell.BellApi.dao;

import com.bell.BellApi.model.Position;

public interface PositionDao {
    Position getByName(String name);
}
