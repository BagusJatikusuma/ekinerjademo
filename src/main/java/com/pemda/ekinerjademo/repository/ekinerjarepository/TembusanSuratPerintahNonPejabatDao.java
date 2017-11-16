package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintahNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintahNonPejabatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface TembusanSuratPerintahNonPejabatDao
        extends JpaRepository<TembusanSuratPerintahNonPejabat, TembusanSuratPerintahNonPejabatId>{
    @Query("select t from TembusanSuratPerintahNonPejabat t " +
            "left join fetch t.suratPerintahNonPejabat " +
            "where t.tembusanSuratPerintahNonPejabatId.kdJabatan = ?1")
    List<TembusanSuratPerintahNonPejabat> findByTembusanSuratPerintahNonPejabatId_KdJabatan(String kdJabatan);

    List<TembusanSuratPerintahNonPejabat> findByTembusanSuratPerintahNonPejabatId_KdSuratPerintah(String kdSuratPerintah);
}
