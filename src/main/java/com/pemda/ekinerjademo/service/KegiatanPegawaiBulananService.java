package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.KegiatanPegawaiBulananId;
import com.pemda.ekinerjademo.wrapper.input.KegiatanPegawaiBulananInputWrapper;

import java.util.List;

public interface KegiatanPegawaiBulananService {
    void create(KegiatanPegawaiBulanan kegiatanPegawaiBulanan);
    void create(List<KegiatanPegawaiBulananInputWrapper> inputWrappers);
    void delete(KegiatanPegawaiBulananId id);

    KegiatanPegawaiBulanan getKegiatanBulananPegawai(KegiatanPegawaiBulananId id);
    List<KegiatanPegawaiBulanan> getKegiatanBulananPegawai(String nipPegawai);
    List<KegiatanPegawaiBulanan> getKegiatanBulananPegawai(String nipPegawai, Integer bulan, Integer tahun);

    List<KegiatanPegawaiBulanan> getKegiatanBulananUnitKerja(String kdUnitKerja);
    List<KegiatanPegawaiBulanan> getKegiatanBulananUnitKerja(String kdUnitKerja, Integer bulan, Integer tahun);
}
