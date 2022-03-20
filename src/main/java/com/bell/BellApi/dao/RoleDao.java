package com.bell.BellApi.dao;

import com.bell.BellApi.model.role.ERole;
import com.bell.BellApi.model.role.Role;


public interface RoleDao {

    Role getByName(ERole name);
}
