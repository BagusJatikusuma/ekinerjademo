package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratInstruksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Repository
public interface SuratInstruksiDao extends JpaRepository<SuratInstruksi, String> {
//    @Query("select si from SuratInstruksi si " +
//            "left join fetch si.instruksiPegawaiSet " +
//            "left join fetch si.instruksiPejabatSet " +
//            "left join fetch si.suratInstruksiPejabat " +
//            "left join fetch si.suratInstruksiNonPejabat " +
//            "where si.nipPembuat = ?1")
//    List<SuratInstruksi> findByNipPembuat(String nipPembuat);
    List<SuratInstruksi> findByNipPembuat(String nipPembuat);
    @Query("select si from SuratInstruksi si " +
            "left join fetch si.instruksiPegawaiSet " +
            "left join fetch si.instruksiPejabatSet " +
            "left join fetch si.suratInstruksiPejabat " +
            "left join fetch si.suratInstruksiNonPejabat " +
            "where si.kdInstruksi = ?1")
    SuratInstruksi findByKdInstruksi(String kdInstruksi);
}
