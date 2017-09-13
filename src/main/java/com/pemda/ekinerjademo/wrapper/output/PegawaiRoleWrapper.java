package com.pemda.ekinerjademo.wrapper.output;

import com.pemda.ekinerjademo.model.ekinerjamodel.Role;

import java.util.List;

/**
 * Created by bagus on 13/09/17.
 */
public class PegawaiRoleWrapper {
    private RoleWrapper currentRole;
    private List<RoleWrapper> roles;

    public PegawaiRoleWrapper() {}
    public PegawaiRoleWrapper(RoleWrapper currentRole, List<RoleWrapper> roles) {
        this.currentRole = currentRole;
        this.roles = roles;
    }

    public RoleWrapper getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(RoleWrapper currentRole) {
        this.currentRole = currentRole;
    }

    public List<RoleWrapper> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleWrapper> roles) {
        this.roles = roles;
    }
}
