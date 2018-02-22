package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratEdaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratEdaranDao extends JpaRepository<SuratEdaran, String> {
    @Query("select se from SuratEdaran se " +
            "left join fetch se.suratEdaranNonPejabat " +
            "left join fetch se.suratEdaranPejabat " +
            "left join fetch se.suratEdaranSubList " +
            "where se.kdUnitKerja = ?1")
    List<SuratEdaran> findByKdUnitKerja(String kdUnitKerja);
    @Query("select se from SuratEdaran se " +
            "left join fetch se.suratEdaranNonPejabat " +
            "left join fetch se.suratEdaranPejabat " +
            "left join fetch se.suratEdaranSubList " +
            "where se.nipPembuatSurat = ?1")
    List<SuratEdaran> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select se from SuratEdaran se " +
            "left join fetch se.suratEdaranNonPejabat " +
            "left join fetch se.suratEdaranPejabat " +
            "left join fetch se.suratEdaranSubList " +
            "where se.nomorTahun = ?1")
    List<SuratEdaran> findByNomorTahun(Integer nomorTahun);

    @Query("select se from SuratEdaran se " +
            "left join fetch se.suratEdaranNonPejabat " +
            "left join fetch se.suratEdaranPejabat " +
            "left join fetch se.suratEdaranSubList " +
            "where se.kdSuratEdaran = ?1")
    SuratEdaran findByKdSuratEdaran(String kdSuratEdaran);

    @Query("select se from SuratEdaran se " +
            "where se.pathPenilaian like concat(?1,'%')")
    List<SuratEdaran> findByLastTree(String kdSuratEdaranRoot);

}
