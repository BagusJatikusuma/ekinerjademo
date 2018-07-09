package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.Role;

import java.util.List;

/**
 * Created by bagus on 13/09/17.
 */
public interface RoleService {
    Role getRole(String roleId);
    List<Role> getRoles();
}
