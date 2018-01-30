package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratTugas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratTugasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@Repository
public interface TembusanSuratTugasDao
        extends JpaRepository<TembusanSuratTugas, TembusanSuratTugasId> {
    @Query("select tst from TembusanSuratTugas tst " +
            "left join fetch tst.suratTugas " +
            "where tst.tembusanSuratTugasId.kdJabatan = ?1")
    List<TembusanSuratTugas> findByTembusanSuratTugasId_KdJabatan(String kdJabatan);
}
