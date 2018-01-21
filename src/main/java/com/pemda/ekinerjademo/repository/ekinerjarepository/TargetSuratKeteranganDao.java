package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeteranganId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TargetSuratKeteranganDao
        extends JpaRepository<TargetSuratKeterangan, TargetSuratKeteranganId> {

}
