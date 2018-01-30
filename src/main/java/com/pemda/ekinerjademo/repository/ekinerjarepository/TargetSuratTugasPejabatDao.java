package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPejabatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@Repository
public interface TargetSuratTugasPejabatDao
        extends JpaRepository<TargetSuratTugasPejabat, TargetSuratTugasPejabatId>{
    List<TargetSuratTugasPejabat> findByTargetSuratTugasPejabatId_KdJabatan(String kdJabatan);
}
