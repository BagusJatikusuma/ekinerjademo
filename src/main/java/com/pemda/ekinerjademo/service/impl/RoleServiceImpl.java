package com.pemda.ekinerjademo.service.impl;

import com.pemda.ekinerjademo.model.ekinerjamodel.Role;
import com.pemda.ekinerjademo.repository.ekinerjarepository.RoleDao;
import com.pemda.ekinerjademo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bagus on 13/09/17.
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    @Autowired private RoleDao roleDao;

    @Override
    public Role getRole(String roleId) {
        return roleDao.findById(roleId);
    }
}
