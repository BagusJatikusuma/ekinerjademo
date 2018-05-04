package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisi;
import com.pemda.ekinerjademo.model.ekinerjamodel.TargetLembarDisposisiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 19/11/17.
 */
@Repository
public interface TargetLembarDisposisiDao extends JpaRepository<TargetLembarDisposisi, TargetLembarDisposisiId> {
    @Query("select t from TargetLembarDisposisi t " +
            "left join fetch t.lembarDisposisi ld " +
            "left join fetch ld.targetLembarDisposisiSet " +
            "where t.targetLembarDisposisiId.nipPegawai = ?1 and ld.statusAktif = 1")
    List<TargetLembarDisposisi> findByTargetLembarDisposisiId_NipPegawai(String nipPegawai);

    @Query("select t from TargetLembarDisposisi t " +
            "left join fetch t.lembarDisposisi ld " +
            "left join fetch ld.noSuratDisposisi " +
            "where t.targetLembarDisposisiId.nipPegawai = ?1 and ld.statusAktif = 1")
    List<TargetLembarDisposisi> findByTargetDisposisi(String nipPegawai);


}
