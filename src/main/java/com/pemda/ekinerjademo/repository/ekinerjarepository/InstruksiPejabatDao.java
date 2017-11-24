package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.InstruksiPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.InstruksiPejabatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Repository
public interface InstruksiPejabatDao extends JpaRepository<InstruksiPejabat, InstruksiPejabatId> {
    @Query("select ip from InstruksiPejabat ip " +
            "left join fetch ip.suratInstruksi " +
            "where ip.instruksiPejabatId.kdJabatan = ?1")
    List<InstruksiPejabat> findInstruksiPejabatByPejabat(String kdPejabat);
}
