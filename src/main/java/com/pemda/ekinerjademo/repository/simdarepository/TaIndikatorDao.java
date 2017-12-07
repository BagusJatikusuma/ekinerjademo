package com.pemda.ekinerjademo.repository.simdarepository;

import com.pemda.ekinerjademo.model.simdamodel.TaIndikator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 04/12/17.
 */
@Repository
public interface TaIndikatorDao extends JpaRepository<TaIndikator, Long> {

}
