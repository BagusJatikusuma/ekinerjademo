package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratInstruksiNonPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Repository
public interface SuratInstruksinonPejabatDao extends JpaRepository<SuratInstruksiNonPejabat, String> {
    @Query("select sinp from SuratInstruksiNonPejabat sinp " +
            "left join fetch sinp.suratInstruksi where sinp.kdUnitKerja = ?1")
    List<SuratInstruksiNonPejabat> findSuratInstruksiNonPejabatByUnitKerja(String kdUnitKerja);
}
