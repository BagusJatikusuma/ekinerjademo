package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPejabatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 28/11/17.
 */
@Repository
public interface TargetSuratPerintahPejabatDao
        extends JpaRepository<TargetSuratPerintahPejabat, TargetSuratPerintahPejabatId>{
    @Query("select t from TargetSuratPerintahPejabat t " +
            "left join fetch t.suratPerintah " +
            "where t.targetSuratPerintahPejabatId.kdJabatan = ?1")
    List<TargetSuratPerintahPejabat> findByTargetSuratPerintahPejabatId_KdJabatan(String kdJabatan);

    List<TargetSuratPerintahPejabat> findByTargetSuratPerintahPejabatId_KdSuratPerintah(String kdSuratPerintah);
}
