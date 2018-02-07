package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.PenanggungJawabKegiatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 07/02/18.
 */
@Repository
public interface PenanggungJawabKegiatanDao
        extends JpaRepository<PenanggungJawabKegiatan, PenanggungJawabKegiatanId> {
    @Query("select pjk from PenanggungJawabKegiatan pjk " +
            "left join fetch pjk.statusPenanggungJawabKegiatan")
    List<PenanggungJawabKegiatan> findAll();

    @Query("select pjk from PenanggungJawabKegiatan pjk " +
            "left join fetch pjk.statusPenanggungJawabKegiatan " +
            "where pjk.penanggungJawabKegiatanId.kdUrusan = ?1 " +
            "and pjk.penanggungJawabKegiatanId.kdBidang = ?2 " +
            "and pjk.penanggungJawabKegiatanId.kdUnit = ?3")
    List<PenanggungJawabKegiatan> findByUnitKerja(String kdUrusan, String kdBidang, String kdUnit);
}
