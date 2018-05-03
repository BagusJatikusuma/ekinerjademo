package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratPerintahPegawaiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 28/11/17.
 */
@Repository
public interface TargetSuratPerintahPegawaiDao
        extends JpaRepository<TargetSuratPerintahPegawai, TargetSuratPerintahPegawaiId> {
    @Query("select t from TargetSuratPerintahPegawai t " +
            "left join fetch t.suratPerintah " +
            "where t.targetSuratPerintahPegawaiId.nipPegawai = ?1")
    List<TargetSuratPerintahPegawai> findByTargetSuratPerintahPegawaiId_NipPegawai(String nipPegawai);

    List<TargetSuratPerintahPegawai> findByTargetSuratPerintahPegawaiId_KdSuratPerintah(String kdSuratperintah);

    @Query("select t from TargetSuratPerintahPegawai t " +
            "left join fetch t.suratPerintah " +
            "where t.kdUnitKerjaTarget = ?1")
    List<TargetSuratPerintahPegawai> findByUnitKerjaTarget(String kdUnitKerjaTarget);
}
