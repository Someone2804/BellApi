package com.bell.BellApi.dao;

import com.bell.BellApi.model.Position;


/**
 * DAO for {@link Position}
 */
public interface PositionDao {

    /**
     * Get position by name
     * @param name
     * @return {@link Position}
     */
    Position getByName(String name);
}
