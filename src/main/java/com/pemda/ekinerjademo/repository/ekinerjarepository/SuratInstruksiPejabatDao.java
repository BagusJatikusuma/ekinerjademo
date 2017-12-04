package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratInstruksiPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Repository
public interface SuratInstruksiPejabatDao extends JpaRepository<SuratInstruksiPejabat, String> {
    @Query("select sip from SuratInstruksiPejabat sip " +
            "left join fetch sip.suratInstruksi " +
            "where sip.kdJabatan = ?1")
    List<SuratInstruksiPejabat> findSuratInstruksiPejabatByPejabat(String kdJabatan);

}
