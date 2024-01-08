package com.ra.service;

import com.ra.model.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
