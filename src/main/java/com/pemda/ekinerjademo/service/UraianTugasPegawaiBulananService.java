package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;

import java.util.Date;
import java.util.List;

public interface UraianTugasPegawaiBulananService {
    void create(UraianTugasPegawaiBulanan uraianTugasPegawaiBulanan);
    void create(UraianTugasPegawaiBulananInputWrapper inputWrapper, Integer statusApproval);
    void create(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers, Integer statusApproval);
    void updateTargetSKPBulananPegawai(UraianTugasPegawaiBulananId urtugId, Integer targetBaru);
    void approveAjuanKontrakBulanan(UraianTugasPegawaiBulananInputWrapper inputWrapper);
    void approveAjuanKontrakBulanan(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers);
    void deleteById(UraianTugasPegawaiBulananId id);

    UraianTugasPegawaiBulanan getById(UraianTugasPegawaiBulananId id);

    List<UraianTugasPegawaiBulanan> getByNip(String nipPegawai);

    List<UraianTugasPegawaiBulanan> getByNip(String nipPegawai, Integer bulan);
    List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja);
    List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja, Integer bulan);
    List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja, Integer bulan, Integer tahun);
    List<UraianTugasPegawaiBulanan> getAjuanUraianTugaspegawaiBulanan(String nipPegawai);

    List<UraianTugasPegawaiBulanan> getAjuanKontrakBulanByNipPegawai(String nipPegawai, int bulan, int tahun);
    List<UraianTugasPegawaiBulanan> getAll(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
}

