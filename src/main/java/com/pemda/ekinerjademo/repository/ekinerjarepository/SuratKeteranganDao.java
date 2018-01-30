package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeterangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratKeteranganDao extends JpaRepository<SuratKeterangan, String> {
    @Query("select distinct sk from SuratKeterangan sk " +
            "left join fetch sk.targetSuratKeteranganList " +
            "where sk.kdUnitKerja = ?1")
    List<SuratKeterangan> findByKdUnitKerja(String kdUnitKerja);
    @Query("select distinct sk from SuratKeterangan sk " +
            "left join fetch sk.targetSuratKeteranganList " +
            "where sk.nipPembuatSurat = ?1")
    List<SuratKeterangan> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select distinct sk from SuratKeterangan sk " +
            "left join fetch sk.targetSuratKeteranganList " +
            "where sk.nomorTahun = ?1")
    List<SuratKeterangan> findByNomorTahun(Integer nomorTahun);

    @Query("select sk from SuratKeterangan sk " +
            "left join fetch sk.targetSuratKeteranganList " +
            "where sk.kdSuratKeterangan = ?1")
    SuratKeterangan findByKdSuratKeterangan(String kdSuratKeterangan);

    @Query("select sk from SuratKeterangan sk " +
            "where sk.pathPenilaian like concat('%',?1)")
    List<SuratKeterangan> findByLastTree(String kdSuratKeterangan);
}
