package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratDinas;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratDinasId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TembusanSuratDinasDao
        extends JpaRepository<TembusanSuratDinas, TembusanSuratDinasId> {
    @Query("select tsd from TembusanSuratDinas tsd " +
            "left join fetch tsd.suratDinas " +
            "where tsd.tembusanSuratDinasId.kdJabatan = ?1")
    List<TembusanSuratDinas> findByTembusanSuratDinasId_KdJabatan(String kdJabatan);

    @Query("select tsd from TembusanSuratDinas tsd " +
            "left join fetch tsd.suratDinas " +
            "where tsd.kdUnitKerja = ?1")
    List<TembusanSuratDinas> findByUnitKerja(String kdUnitKerja);
}