package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratKeputusan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
@Repository
public interface SuratKeputusanDao extends JpaRepository<SuratKeputusan, String> {
    List<SuratKeputusan> findByKdUnitKerja(String kdUnitKerja);
    List<SuratKeputusan> findByNipPembuatSurat(String nipPembuatSurat);
    List<SuratKeputusan> findByNomorTahun(Integer nomorTahun);

    SuratKeputusan findByKdSuratKeputusan(String kdSuratKeputusan);
}
