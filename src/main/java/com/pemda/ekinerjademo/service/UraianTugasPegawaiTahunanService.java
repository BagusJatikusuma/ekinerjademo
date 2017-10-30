package com.pemda.ekinerjademo.service;

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
    void approveUrtug(UraianTugasPegawaiTahunanId uraianTugasPegawaiTahunanId);
    void createUraianTugasPegawaiTahunan(UraianTugasPegawaiTahunanInputWrapper uraianTugasPegawaiTahunanInputWrapper, Boolean statusApproval);
    List<UraianTugasPegawaiTahunan> getByNipPegawai(String nipPegawai);
    List<UraianTugasPegawaiTahunan> getByUnitKerja(String kdUnitKerja);
}
