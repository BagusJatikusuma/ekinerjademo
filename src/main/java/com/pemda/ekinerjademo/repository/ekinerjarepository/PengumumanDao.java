package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Pengumuman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface PengumumanDao extends JpaRepository<Pengumuman, String> {
    List<Pengumuman> findByKdUnitKerja(String kdUnitKerja);
    List<Pengumuman> findByNipPembuatSurat(String nipPembuatSurat);
    List<Pengumuman> findByNomorTahun(Integer nomorTahun);

    Pengumuman findByKdPengumuman(String kdPengumuman);

    @Query("select pm from Pengumuman pm " +
            "where pm.pathPenilaian like concat(?1,'%')")
    List<Pengumuman> findBylastTree(String kdPengumumanRoot);

}
