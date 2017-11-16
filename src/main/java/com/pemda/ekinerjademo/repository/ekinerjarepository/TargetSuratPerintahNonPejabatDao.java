package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahNonPejabatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface TargetSuratPerintahNonPejabatDao
        extends JpaRepository<TargetSuratPerintahNonPejabat, TargetSuratPerintahNonPejabatId> {
    @Query("select t from TargetSuratPerintahNonPejabat t " +
            "left join fetch t.suratPerintahNonPejabat " +
            "where t.targetSuratPerintahNonPejabatId.nipPegawai = ?1")
    List<TargetSuratPerintahNonPejabat> findByTargetSuratPerintahNonPejabatId_NipPegawai(String nipPegawai);

    List<TargetSuratPerintahNonPejabat> findByTargetSuratPerintahNonPejabatId_KdSuratPerintah(String kdSuratPerintah);
}
