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
    List<PenanggungJawabKegiatan> findByUnitKerja(Integer kdUrusan, Integer kdBidang, Integer kdUnit);

    @Query("select pjk from PenanggungJawabKegiatan pjk " +
            "left join fetch pjk.statusPenanggungJawabKegiatan " +
            "where pjk.penanggungJawabKegiatanId.kdUrusan = ?1 " +
            "and pjk.penanggungJawabKegiatanId.kdBidang = ?2 " +
            "and pjk.penanggungJawabKegiatanId.kdUnit = ?3 " +
            "and pjk.penanggungJawabKegiatanId.kdSub = ?4 " +
            "and pjk.penanggungJawabKegiatanId.tahun = ?5 " +
            "and pjk.penanggungJawabKegiatanId.kdProg = ?6 " +
            "and pjk.penanggungJawabKegiatanId.idProg = ?7 " +
            "and pjk.penanggungJawabKegiatanId.kdKeg = ?8")
    List<PenanggungJawabKegiatan> findByKegiatan(Integer kdUrusan,
                                                 Integer kdBidang,
                                                 Integer kdUnit,
                                                 Integer kdSub,
                                                 Integer tahun,
                                                 Integer kdProg,
                                                 Integer idProg,
                                                 Integer kdKeg);
}
