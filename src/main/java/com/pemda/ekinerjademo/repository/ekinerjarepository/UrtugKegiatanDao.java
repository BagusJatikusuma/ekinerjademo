package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
@Repository
public interface UrtugKegiatanDao extends JpaRepository<UrtugKegiatan, Long> {
    UrtugKegiatan findByUrtugKegiatanId(UrtugKegiatanId urtugKegiatanId);
    void deleteByUrtugKegiatanId(UrtugKegiatanId urtugKegiatanId);
    List<UrtugKegiatan> findByUrtugKegiatanId_KdUrtugAndUrtugKegiatanId_KdJabatanAndUrtugKegiatanId_KdJenisUrtugAndUrtugKegiatanId_TahunUrtug(
            String kdUrtug,
            String kdJabatan,
            String kdJenisUrtug,
            Integer tahunUrtug);
}
