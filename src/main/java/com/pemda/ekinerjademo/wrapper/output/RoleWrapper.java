package com.pemda.ekinerjademo.wrapper.output;

/**
 * Created by bagus on 13/09/17.
 */
public class RoleWrapper {
    private String id;
    private String role;

    public RoleWrapper() {}
    public RoleWrapper(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
