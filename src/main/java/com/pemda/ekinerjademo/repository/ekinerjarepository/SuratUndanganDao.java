package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratUndangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratUndanganDao extends JpaRepository<SuratUndangan, String> {
    @Query("select distinct su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdUnitKerja = ?1")
    List<SuratUndangan> findByKdUnitKerja(String kdUnitKerja);
    @Query("select distinct su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdUnitKerjaTarget = ?1")
    List<SuratUndangan> findByKdUnitKerjaTarget(String kdUnitKerjaTarget);
    @Query("select distinct su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.nipPembuatSurat = ?1")
    List<SuratUndangan> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select distinct su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.nomorTahun = ?1")
    List<SuratUndangan> findByNomorTahun(Integer nomorTahun);

    @Query("select distinct su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.nipPenerimaSuratUndangan = ?1")
    List<SuratUndangan> findByNipPenerimaSuratUndangan(String nipPenerimaSuratUndangan);

    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdSuratUndangan = ?1")
    SuratUndangan findByKdSuratUndangan(String kdSuratUndangan);

    @Query("select su from SuratUndangan su " +
            "where su.pathPenilaian like concat(?1,'%') ")
    List<SuratUndangan> findByLastTree(String kdSuratUndanganRoot);

    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdUnitKerja = ?1 " +
            "and su.approvalPenandatangan = 1")
    List<SuratUndangan> findSuratUndanganApproval(String kdUnitKerja);

}
