package com.pemda.ekinerjademo.repository.bismarepository;

import com.pemda.ekinerjademo.model.bismamodel.QutPegawai;
import com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by bagus on 09/09/17.
 */
@Repository
public interface QutPegawaiDao extends JpaRepository<QutPegawai, Long>{
    QutPegawai findByNip(String nip);
    List<QutPegawai> findByKdUnitKerja(String kdUnitKerja);
    @Query("select " +
            "new com.pemda.ekinerjademo.projection.ekinerjaprojection.CustomPegawaiCredential(q.nip, q.nama, q.gol, q.pangkat, q.kdJabatan, q.jabatan, q.kdUnitKerja, q.unitKerja) " +
            "from QutPegawai q")
    List<CustomPegawaiCredential> findByCustomPegawaiCredential();
}
