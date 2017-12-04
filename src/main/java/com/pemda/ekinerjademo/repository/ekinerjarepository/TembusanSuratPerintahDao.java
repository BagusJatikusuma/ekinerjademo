package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintah;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratPerintahId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface TembusanSuratPerintahDao
        extends JpaRepository<TembusanSuratPerintah, TembusanSuratPerintahId>{
    @Query("select t from TembusanSuratPerintah t " +
            "left join fetch t.suratPerintah " +
            "where t.tembusanSuratPerintahId.kdJabatan = ?1")
    List<TembusanSuratPerintah> findByTembusanSuratPerintahId_KdJabatan(String kdJabatan);

    List<TembusanSuratPerintah> findByTembusanSuratPerintahId_KdSuratPerintah(String kdSuratPerintah);
}
