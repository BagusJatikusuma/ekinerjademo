package com.pemda.ekinerjademo.repository.ekinerjarepository;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bagus on 05/10/17.
 */
@Repository
public interface UrtugKegiatanPegawaiDao extends JpaRepository<UrtugKegiatanPegawai, String> {
    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatanPegawaiId.nipPegawai = ?1")
    List<UrtugKegiatanPegawai> findUrtugKegiatanPegawaiByNipPegawai(String nipPegawai);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where utjj.uraianTugasJabatan.uraianTugasJabatanId = ?1 " +
            "and ukp.urtugKegiatanPegawaiId.nipPegawai = ?2")
    List<UrtugKegiatanPegawai> findUrtugKegiatanPegawaiByUrtugJabatanAndNip(
            UraianTugasJabatanId uraianTugasJabatanId, String nipPegawai);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatan.uraianTugasJabatanJenisUrtug.uraianTugasJabatanJenisUrtugId = ?1 " +
            "and ukp.urtugKegiatanPegawaiId.nipPegawai = ?2")
    List<UrtugKegiatanPegawai> findUrtugKegiatanPegawaiByUrtugJabatanTahunAndNip(
            UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId, String nip);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatan.urtugKegiatanId = ?1")
    List<UrtugKegiatanPegawai> findUrtugKegiatanPegawaiByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatan.uraianTugasJabatanJenisUrtug.uraianTugasJabatanJenisUrtugId = ?1")
    List<UrtugKegiatanPegawai> findUrtugKegiatanPegawaiByUrtugJabatan(UraianTugasJabatanJenisUrtugId urtugjabatanJenisId);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatanPegawaiId.kdJabatan like concat(?1,'%')")
    List<UrtugKegiatanPegawai> findByUnitKerja(String kdUnitKerja);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatanPegawaiId.kdUrusan = ?1 " +
            "and ukp.urtugKegiatanPegawaiId.kdBidang = ?2 " +
            "and ukp.urtugKegiatanPegawaiId.kdUnit = ?3 " +
            "and ukp.urtugKegiatanPegawaiId.kdSub = ?4 " +
            "and ukp.urtugKegiatanPegawaiId.tahun = ?5 " +
            "and ukp.urtugKegiatanPegawaiId.kdProg = ?6 " +
            "and ukp.urtugKegiatanPegawaiId.idProg = ?7")
    List<UrtugKegiatanPegawai> findByProgram(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatanPegawaiId.kdUrtug = ?1 " +
            "and ukp.urtugKegiatanPegawaiId.kdJabatan = ?2 " +
            "and ukp.urtugKegiatanPegawaiId.kdJenisUrtug = ?3 " +
            "and ukp.urtugKegiatanPegawaiId.tahunUrtug = ?4 " +
            "and ukp.urtugKegiatanPegawaiId.kdUrusan = ?5 " +
            "and ukp.urtugKegiatanPegawaiId.kdBidang = ?6 " +
            "and ukp.urtugKegiatanPegawaiId.kdUnit = ?7 " +
            "and ukp.urtugKegiatanPegawaiId.kdSub = ?8 " +
            "and ukp.urtugKegiatanPegawaiId.tahun = ?9 " +
            "and ukp.urtugKegiatanPegawaiId.kdProg = ?10 " +
            "and ukp.urtugKegiatanPegawaiId.idProg = ?11")
    List<UrtugKegiatanPegawai> findByProgramAndUrtugJabatan(String kdUrtug, String kdJabatan, String kdJenisUrtug, Integer tahunUrtug, Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg);

    @Query("select ukp from UrtugKegiatanPegawai ukp " +
            "left join fetch ukp.urtugKegiatan uk " +
            "left join fetch uk.uraianTugasJabatanJenisUrtug utjj " +
            "left join fetch utjj.uraianTugasJabatan utj " +
            "left join fetch utjj.jenisUrtug " +
            "left join fetch utj.uraianTugas " +
            "where ukp.urtugKegiatanPegawaiId.kdUrusan = ?1 " +
            "and ukp.urtugKegiatanPegawaiId.kdBidang = ?2 " +
            "and ukp.urtugKegiatanPegawaiId.kdUnit = ?3 " +
            "and ukp.urtugKegiatanPegawaiId.kdSub = ?4 " +
            "and ukp.urtugKegiatanPegawaiId.tahun = ?5 " +
            "and ukp.urtugKegiatanPegawaiId.kdProg = ?6 " +
            "and ukp.urtugKegiatanPegawaiId.idProg = ?7 " +
            "and ukp.urtugKegiatanPegawaiId.nipPegawai = ?8")
    List<UrtugKegiatanPegawai> findByProgramAndPegawai(Integer kdUrusan, Integer kdBidang, Integer kdUnit, Integer kdSub, Integer tahun, Integer kdProg, Integer idProg, String nip);

    void deleteByUrtugKegiatanPegawaiId(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
}
