package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtug;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasJabatanJenisUrtugId;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunan;
import com.pemda.ekinerjademo.model.ekinerjamodel.UraianTugasPegawaiTahunanId;
import com.pemda.ekinerjademo.wrapper.input.UraianTugasPegawaiTahunanInputWrapper;

import java.util.List;

/**
 * Created by bagus on 14/10/17.
 */
public interface UraianTugasPegawaiTahunanService {
    void createUraianTugasPegawaiTahunanList(List<UraianTugasPegawaiTahunanInputWrapper> urtugPegawaiList);
    void deleteUraianTugasPegawaiTahunan(UraianTugasPegawaiTahunanId urtugPegawaiTahunanId);
    void deleteUraianTugasPegawaiTahunanByNipPegawaiAndTahun(String nipPegawai, Integer tahunUrtug);
    void approveUrtug(UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper);
    void createUraianTugasPegawaiTahunan(
            UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper,
            Integer statusApproval);
    List<UraianTugasPegawaiTahunan> getByNipPegawai(String nipPegawai);
    List<UraianTugasPegawaiTahunan> getByUnitKerja(String kdUnitKerja);
    List<UraianTugasPegawaiTahunan> getUraianTugasPegawaiYangDiAjukan(String nipPegawai);
    List<UraianTugasPegawaiTahunan> getAll(UraianTugasJabatanJenisUrtugId uraianTugasJabatanJenisUrtugId);
}
