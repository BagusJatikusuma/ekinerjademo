package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
public interface UrtugKegiatanPegawaiService {
    List<UrtugKegiatanPegawai> findByUrtugJabatan(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
    List<UrtugKegiatanPegawai> findByNipPegawai(String nipPegawai);
    List<UrtugKegiatanPegawai> findByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId);
    void save(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void update(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void delete(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
}
