package com.pemda.ekinerjademo.service;

import com.pemda.ekinerjademo.model.ekinerjamodel.*;

import java.util.List;
import java.util.Set;

/**
 * Created by bagus on 14/11/17.
 */
public interface SuratPerintahService {
    Set<SuratPerintah> getByKdUnitKerja(String kdUnitKerja);
    Set<SuratPerintah> getByNomorTahun(String nomorTahun);
    Set<SuratPerintah> getByNipPembuat(String nipPembuat);

    List<TargetSuratPerintahPegawai> getTargetSuratPerintahPegawai(String nipTargetSurat);
    List<TargetSuratPerintahPejabat> getTargetSuratPerintahPejabat(String kdJabatanTarget);

    Integer getLatestNomorSuratByUnitKerja(String kdUnitKerja);

    void creteSurat(SuratPerintah suratPerintah);
    void createTargetSuratPegawai(Set<TargetSuratPerintahPegawai> targetSuratPerintahPegawaiSet);
    void createTargetSuratPejabat(Set<TargetSuratPerintahPejabat> targetSuratPerintahPejabatSet);
    void createSuratPerintahPejabat(SuratPerintahPejabat suratPerintahPejabat);
    void createSuratPerintahNonPejabat(SuratPerintahNonPejabat suratPerintahNonPejabat);
    void createTembusanSurat(Set<TembusanSuratPerintah> tembusanSuratPerintahSet);

    SuratPerintah getSuratPerintahByKdSuratPerintah(String kdSuratperintah);

}
