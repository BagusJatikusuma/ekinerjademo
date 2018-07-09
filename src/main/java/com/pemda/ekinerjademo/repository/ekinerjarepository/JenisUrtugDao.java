package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.JenisUrtug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 27/09/17.
 */
@Repository
public interface JenisUrtugDao extends JpaRepository<JenisUrtug, Long> {
}
