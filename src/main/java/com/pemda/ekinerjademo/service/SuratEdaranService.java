package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.SuratEdaran;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratEdaranNonPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratEdaranPejabat;
import com.pemda.ekinerjademo.model.ekinerjamodel.SuratEdaranSub;

import java.util.List;

/**
 * Created by bayu on 17/01/18.
 */
public interface SuratEdaranService {
    List<SuratEdaran> getByKdUnitKerja(String kdUnitKerja);
    List<SuratEdaran> getByNipPembuat(String nipPembuat);
    List<SuratEdaran> getByNomorTahun(Integer nomorTahun);
    List<SuratEdaran> getSuratEdaranSekretarisApproval(String kdUnitKerja);

    SuratEdaran getByKdSuratEdaran(String kdSuratEdaran);

    void create(SuratEdaran suratEdaran);
    void createSuratEdaranNonPejabat(SuratEdaranNonPejabat suratEdaranNonPejabat);
    void createSuratEdaranPejabat(SuratEdaranPejabat suratEdaranPejabat);
    void createSuratEdaranSub(SuratEdaranSub suratEdaranSub);

    void approveSuratEdaran(String kdSuratEdaran);

}
