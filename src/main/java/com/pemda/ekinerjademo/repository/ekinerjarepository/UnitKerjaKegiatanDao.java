package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UnitKerjaKegiatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bagus on 07/10/17.
 */
@Repository
public interface UnitKerjaKegiatanDao extends JpaRepository<UnitKerjaKegiatan, String> {
    UnitKerjaKegiatan findByKdUnitKerja(String kdUnitKerja);
}
