package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKuasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface SuratKuasaDao extends JpaRepository<SuratKuasa, String> {
    List<SuratKuasa> findByNipPembuatSurat(String nipPembuatSurat);
    List<SuratKuasa> findByNipPenerimaKuasa(String nipPenerimaKuasa);

    @Query("select sk from SuratKuasa sk " +
            "where sk.pathPenilaian like concat(?1,'%')")
    List<SuratKuasa> findByLastTree(String kdSuratKuasaRoot);

    @Query("select sk from SuratKuasa sk " +
            "where sk.kdUnitKerja = ?1 " +
            "and sk.approvalSekretaris = 1")
    List<SuratKuasa> findBySekretarisApproval(String kdUnitKerja);
}
