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

    void deleteByUrtugKegiatanPegawaiId(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
}
