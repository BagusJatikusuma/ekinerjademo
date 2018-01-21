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
    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdUnitKerja = ?1")
    List<SuratUndangan> findByKdUnitKerja(String kdUnitKerja);
    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.nipPembuatSurat = ?1")
    List<SuratUndangan> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.nomorTahun = ?1")
    List<SuratUndangan> findByNomorTahun(Integer nomorTahun);

    @Query("select su from SuratUndangan su " +
            "left join fetch su.suratUndanganNonPejabat " +
            "left join fetch su.suratUndanganPejabat " +
            "left join fetch su.tembusanSuratUndanganList " +
            "where su.kdSuratUndangan = ?1")
    SuratUndangan findByKdSuratUndangan(String kdSuratUndangan);

}
