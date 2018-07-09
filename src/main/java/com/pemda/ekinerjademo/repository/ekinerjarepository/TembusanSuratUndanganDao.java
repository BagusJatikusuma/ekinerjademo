package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratUndangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TembusanSuratUndanganId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TembusanSuratUndanganDao
        extends JpaRepository<TembusanSuratUndangan, TembusanSuratUndanganId> {
    @Query("select tsu from TembusanSuratUndangan tsu " +
            "left join fetch tsu.suratUndangan " +
            "where tsu.tembusanSuratUndanganId.kdJabatan = ?1")
    List<TembusanSuratUndangan> findByTembusanSuratUndanganId_KdJabatan(String kdJabatan);

    @Query("select tsu from TembusanSuratUndangan tsu " +
            "left join fetch tsu.suratUndangan " +
            "where tsu.kdUnitKerja = ?1")
    List<TembusanSuratUndangan> findByUnitKerja(String kdUnitKerja);
}
