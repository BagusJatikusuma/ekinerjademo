package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select uk from UrtugKegiatan uk " +
            "left join fetch uk.penanggungJawabKegiatan pjk " +
            "left join fetch pjk.statusPenanggungJawabKegiatan " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where uk.urtugKegiatanId.nipPegawai = ?1 " +
            "and uk.urtugKegiatanId.tahunUrtug = ?2 " +
            "and pjk.statusApproval = 1")
    List<UrtugKegiatan> findByPegawaiApproval(String nipPegawai, Integer tahunUrtug);

    @Query("select uk from UrtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where uk.urtugKegiatanId.kdUrtug = ?1 " +
            "and uk.urtugKegiatanId.kdJabatan = ?2 " +
            "and uk.urtugKegiatanId.kdJenisUrtug = ?3 " +
            "and uk.urtugKegiatanId.tahunUrtug = ?4 " +
            "and uk.urtugKegiatanId.kdUrusan = ?5 " +
            "and uk.urtugKegiatanId.kdBidang = ?6 " +
            "and uk.urtugKegiatanId.kdUnit = ?7 " +
            "and uk.urtugKegiatanId.kdSub = ?8 " +
            "and uk.urtugKegiatanId.tahun = ?9 " +
            "and uk.urtugKegiatanId.kdProg = ?10 " +
            "and uk.urtugKegiatanId.idProg = ?11")
    List<UrtugKegiatan> findByProgramAndUrtug(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg);

    @Query("select uk from UrtugKegiatan uk "+
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where uk.urtugKegiatanId.nipPegawai = ?1 " +
            "and uk.urtugKegiatanId.kdUrusan = ?2 " +
            "and uk.urtugKegiatanId.kdBidang = ?3 " +
            "and uk.urtugKegiatanId.kdUnit = ?4 ")
    List<UrtugKegiatan> findByPegawawiAndUnitKerja(String nipPegawai, Integer kdUrusan, Integer kdBidang, Integer kdUnit);

    @Query("select uk from UrtugKegiatan uk " +
            "left join fetch uk.penanggungJawabKegiatan pjk " +
            "left join fetch pjk.statusPenanggungJawabKegiatan " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where uk.urtugKegiatanId.kdUrtug = ?1 " +
            "and uk.urtugKegiatanId.kdJabatan = ?2 " +
            "and uk.urtugKegiatanId.kdJenisUrtug = ?3 " +
            "and uk.urtugKegiatanId.tahunUrtug = ?4 " +
            "and uk.urtugKegiatanId.nipPegawai = ?5 ")
    List<UrtugKegiatan> findByUrtugJabatanJenisAndNipPegawai(String kdUrtug,
                                                             String kdJabatan,
                                                             String kdJenisUrtug,
                                                             Integer tahunUrtug,
                                                             String nipPegawai);
}
