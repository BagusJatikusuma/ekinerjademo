package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratTugasPegawaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 30/01/18.
 */
@Repository
public interface TargetSuratTugasPegawaiDao
        extends JpaRepository<TargetSuratTugasPegawai, TargetSuratTugasPegawaiId>{
    @Query("select tst from TargetSuratTugasPegawai tst " +
            "left join fetch tst.suratTugas " +
            "where tst.targetSuratTugasPegawaiId.nipPegawai = ?1")
    List<TargetSuratTugasPegawai> findByTargetSuratTugasPegawaiId_NipPegawai(String nipPegawai);
}
