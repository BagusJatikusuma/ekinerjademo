package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeterangan;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetSuratKeteranganId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface TargetSuratKeteranganDao
        extends JpaRepository<TargetSuratKeterangan, TargetSuratKeteranganId> {

    @Query("select tsk from TargetSuratKeterangan tsk " +
            "left join fetch tsk.suratKeterangan " +
            "where tsk.targetSuratKeteranganId.nipPegawai = ?1")
    List<TargetSuratKeterangan> findByTargetSuratKeteranganId_NipPegawai(String nipPegawai);

}
