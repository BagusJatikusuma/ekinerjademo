package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.QutPegawaiClone;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bayu on 10/12/17.
 */
public interface QutPegawaiCloneDao extends JpaRepository<QutPegawaiClone, Long> {
    QutPegawaiClone findByNip(String nip);
    List<QutPegawaiClone> findByKdUnitKerja(String kdUnitKerja);
    @Query("select " +
            "new com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential(q.nip, q.nama, q.gol, q.pangkat, q.kdJabatan, q.jabatan, q.kdUnitKerja, q.unitKerja, q.glrDpn, q.glrBlk) " +
            "from QutPegawaiClone q")
    List<CustomPegawaiCredential> findByCustomPegawaiCredential();
    List<QutPegawaiClone> findByKdJabatan(String kdJabatan);
}
