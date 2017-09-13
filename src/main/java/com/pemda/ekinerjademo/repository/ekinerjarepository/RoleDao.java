package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 13/09/17.
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findById(String roleId);
}
