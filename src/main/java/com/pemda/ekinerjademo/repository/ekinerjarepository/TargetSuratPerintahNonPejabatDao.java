package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPegawaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface TargetSuratPerintahNonPejabatDao
        extends JpaRepository<TargetSuratPerintahPegawai, TargetSuratPerintahPegawaiId> {
//    @Query("select t from TargetSuratPerintahPegawai t " +
//            "left join fetch t.suratPerintahNonPejabat " +
//            "where t.targetSuratPerintahNonPejabatId.nipPegawai = ?1")
//    List<TargetSuratPerintahPegawai> findByTargetSuratPerintahNonPejabatId_NipPegawai(String nipPegawai);
//
//    List<TargetSuratPerintahPegawai> findByTargetSuratPerintahNonPejabatId_KdSuratPerintah(String kdSuratPerintah);
}
