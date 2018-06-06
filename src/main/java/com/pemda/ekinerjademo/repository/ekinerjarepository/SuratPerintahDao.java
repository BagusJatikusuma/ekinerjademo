package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPerintah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
@Repository
public interface SuratPerintahDao extends JpaRepository<SuratPerintah, String> {
    Set<SuratPerintah> findByKdUnitKerja(String kdUnitKerja);
    Set<SuratPerintah> findByNomorTahun(String nomorTahun);
    Set<SuratPerintah> findByNipPembuat(String nipPembuat);
    @Query("select s from SuratPerintah s " +
            "left join fetch s.targetSuratPerintahPegawaiList " +
            "left join fetch s.targetSuratPerintahPejabatSet " +
            "left join fetch s.suratPerintahPejabat " +
            "left join fetch s.suratPerintahNonPejabat " +
            "left join fetch s.tembusanSuratPerintahList " +
            "where s.kdSuratPerintah = ?1")
    SuratPerintah findByKdSuratPerintah(String kdSuratperintah);

    @Query("select s from SuratPerintah s " +
            "left join fetch s.targetSuratPerintahPegawaiList " +
            "left join fetch s.targetSuratPerintahPejabatSet " +
            "left join fetch s.suratPerintahPejabat " +
            "left join fetch s.suratPerintahNonPejabat " +
            "left join fetch s.tembusanSuratPerintahList " +
            "where s.kdUnitKerja = ?1 " +
            "and s.approvalPenandatangan = 1 " +
            "and s.approvalSekretaris = 1")
    List<SuratPerintah> findDraftApproval(String kdUnitKerja);

    @Query("select s from SuratPerintah s " +
            "left join fetch s.targetSuratPerintahPegawaiList " +
            "left join fetch s.targetSuratPerintahPejabatSet " +
            "left join fetch s.suratPerintahPejabat " +
            "left join fetch s.suratPerintahNonPejabat " +
            "left join fetch s.tembusanSuratPerintahList " +
            "where s.kdUnitKerja = ?1 " +
            "and s.approvalSekretaris = 1")
    Set<SuratPerintah> findBySekretarisApproval(String kdUnitKerja);

    @Query("select max(s.nomorSurat1) from SuratPerintah s " +
            "where s.kdUnitKerja = ?1")
    Integer findLatestNomorSuratByUnitKerja(String kdUnitKerja);

    @Query("select sp from SuratPerintah sp " +
            "where sp.pathPenilaian like concat(?1,'%')")
    Set<SuratPerintah> findByLastTree(String kdSuratPerintahRoot);
}
