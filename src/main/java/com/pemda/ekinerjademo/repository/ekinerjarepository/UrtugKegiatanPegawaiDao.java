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
    void deleteByUrtugKegiatanPegawaiId(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
}