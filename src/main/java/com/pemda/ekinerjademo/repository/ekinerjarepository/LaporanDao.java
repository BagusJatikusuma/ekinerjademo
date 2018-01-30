package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by bayu on 07/12/17.
 */
public interface LaporanDao extends JpaRepository<Laporan, String> {
    List<Laporan> findByNipPembuatSurat(String nipPembuatSurat);
    @Query("select lp from Laporan lp " +
            "where lp.pathPenilaian like concat('%',?1)")
    List<Laporan> findByLastTree(String kdLaporan);
}
