package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulanan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiBulananId;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiBulananInputWrapper;

import java.util.List;

public interface UraianTugasPegawaiBulananService {
    void create(UraianTugasPegawaiBulananInputWrapper inputWrapper, Integer statusApproval);
    void create(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers, Integer statusApproval);
    void approveAjuanKontrakBulanan(UraianTugasPegawaiBulananInputWrapper inputWrapper);
    void approveAjuanKontrakBulanan(List<UraianTugasPegawaiBulananInputWrapper> inputWrappers);
    void deleteById(UraianTugasPegawaiBulananId id);

    List<UraianTugasPegawaiBulanan> getByNip(String nipPegawai);
    List<UraianTugasPegawaiBulanan> getByUnitKerja(String unitKerja);
    List<UraianTugasPegawaiBulanan> getAjuanUraianTugaspegawaiBulanan(String nipPegawai);
}
