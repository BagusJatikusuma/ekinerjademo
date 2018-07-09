package com.pemda.ekinerjademo.repository.simdarepository;

import com.pemda.ekinerjademo.model.simdamodel.RefIndikator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 04/12/17.
 */
@Repository
public interface RefIndikatorDao extends JpaRepository<RefIndikator, Long> {

}
