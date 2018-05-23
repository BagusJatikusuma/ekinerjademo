package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.BeritaAcara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface BeritaAcaraDao extends JpaRepository<BeritaAcara, String> {
    List<BeritaAcara> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select ba from BeritaAcara ba " +
            "where ba.pathPenilaian like concat(?1,'%')")
    List<BeritaAcara> findByLastTree(String kdBeritaAcaraRoot);

    @Query("select ba from BeritaAcara ba " +
            "where ba.kdUnitKerja = ?1 " +
            "and ba.approvalSekretaris = 1")
    List<BeritaAcara> findBySekretarisApproval(String kdUnitKerja);
}
