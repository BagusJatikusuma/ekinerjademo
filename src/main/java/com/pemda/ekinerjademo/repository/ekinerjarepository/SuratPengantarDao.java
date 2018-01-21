package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratPengantar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratPengantarDao extends JpaRepository<SuratPengantar, String> {
    @Query("select sp from SuratPengantar sp " +
            "left join fetch sp.suratPengantarIsiList " +
            "where sp.kdUnitKerja = ?1")
    List<SuratPengantar> findByKdUnitKerja(String kdUnitKerja);
    @Query("select sp from SuratPengantar sp " +
            "left join fetch sp.suratPengantarIsiList " +
            "where sp.nipPembuatSurat = ?1")
    List<SuratPengantar> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select sp from SuratPengantar sp " +
            "left join fetch sp.suratPengantarIsiList " +
            "where sp.nomorTahun = ?1")
    List<SuratPengantar> findByNomorTahun(Integer nomorTahun);

    @Query("select sp from SuratPengantar sp " +
            "left join fetch sp.suratPengantarIsiList " +
            "where sp.kdSuratPengantar = ?1")
    SuratPengantar findByKdSuratPengantar(String kdSuratPengantar);
}
