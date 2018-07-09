package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.InstruksiPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.InstruksiPegawaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 22/11/17.
 */
@Repository
public interface InstruksiPegawaiDao extends JpaRepository<InstruksiPegawai, InstruksiPegawaiId> {
    @Query("select ip from InstruksiPegawai ip " +
            "left join fetch ip.suratInstruksi " +
            "where ip.instruksiPegawaiId.nipPegawai = ?1")
    List<InstruksiPegawai> findInstruksiPegawaiByPegawai(String nipPegawai);
}
