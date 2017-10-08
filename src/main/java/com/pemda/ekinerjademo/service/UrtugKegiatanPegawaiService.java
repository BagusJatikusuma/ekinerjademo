package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanPegawai;
import com.pemda.ekinerjademo.model.ekinerjamodel.UrtugKegiatanPegawaiId;

import java.util.List;

/**
 * Created by bagus on 06/10/17.
 */
public interface UrtugKegiatanPegawaiService {
    List<UrtugKegiatanPegawai> findByNipPegawai(String nipPegawai);
    List<UrtugKegiatanPegawai> findByUrtugKegiatan(UrtugKegiatanId urtugKegiatanId);
    void save(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void update(UrtugKegiatanPegawai urtugKegiatanPegawai);
    void delete(UrtugKegiatanPegawaiId urtugKegiatanPegawaiId);
}
