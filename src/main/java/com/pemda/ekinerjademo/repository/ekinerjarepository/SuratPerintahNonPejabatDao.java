package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintahNonPejabat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface SuratPerintahNonPejabatDao extends JpaRepository<SuratPerintahNonPejabat, String> {
    Set<SuratPerintahNonPejabat> findByKdUnitKerja(String kdUnitKerja);
    Set<SuratPerintahNonPejabat> findByNomorTahun(String nomorTahun);
    Set<SuratPerintahNonPejabat> findByNipPembuat(String nipPembuat);
    @Query("select s from SuratPerintahNonPejabat s " +
            "left join fetch s.targetSuratPerintahNonPejabatList " +
            "left join fetch s.tembusanSuratPerintahNonPejabatList " +
            "where s.kdSuratPerintah = ?1")
    SuratPerintahNonPejabat findSuratPerintahNonPejabatByKdSuratPerintah(String kdSuratperintah);

    @Query("select max(s.nomorSurat1) from SuratPerintahNonPejabat s " +
            "where s.kdUnitKerja = ?1")
    Integer findLatestNomorSuratByUnitKerja(String kdUnitKerja);
}
